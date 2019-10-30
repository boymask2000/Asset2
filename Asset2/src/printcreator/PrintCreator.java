package printcreator;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

import common.AnagraficaCampi;
import common.JsfUtil;
import common.Languages;
import common.Pair;
import common.TempFileFactory;
import common.TimeUtil;
import managed.BasicDocumentViewController;

public class PrintCreator {
	private StringBuffer buffer = new StringBuffer();
	public static PageFormat PORTRAT = new PageFormat("PORTRAT");
	public static PageFormat LANDSCAPE = new PageFormat("LANDSCAPE");
	private boolean withPageNumber = true;

	private List<PageFormat> pageFormats = new ArrayList<PageFormat>();

	public PrintCreator() {
		pageFormats.add(PORTRAT);
		LANDSCAPE.setOrientation("90");
		pageFormats.add(LANDSCAPE);
	}

	public void insertPageFormats() {
		buffer.append("<fo:layout-master-set>");
		for (PageFormat f : pageFormats)
			buffer.append(f.getBuffer());
		buffer.append("</fo:layout-master-set>");
	}

	private boolean startedPageSequence = false;

	public void startPageSequence(PageFormat pf) {
		if (pf == null)
			pf = PORTRAT;

		buffer.append("<fo:page-sequence master-reference=\"" + pf.getName() + "\">");

		if (isWithPageNumber()) {
			buffer.append(" <fo:static-content flow-name=\"xsl-region-after\">");
			buffer.append("   <fo:block text-align=\"center\">");
			buffer.append("      Page <fo:page-number/>");
			buffer.append("     </fo:block>");
			buffer.append("   </fo:static-content>");
		}
		buffer.append("<fo:flow flow-name=\"xsl-region-body\">");
		startedPageSequence = true;
	}

	public void endPageSequence() {
		if (!startedPageSequence)
			return;
		startedPageSequence = false;
		buffer.append("</fo:flow>");

		buffer.append("</fo:page-sequence>");

	}

	public void insertStartDoc() {
		buffer.append("<fo:root xmlns:fo=\"http://www.w3.org/1999/XSL/Format\">");

	}

	public void insertFineDoc() {
		buffer.append("</fo:root>");

	}

	public void addBlock(String s, String size) {
		buffer.append("<fo:block font-size=\"" + size + "\">");
		buffer.append(s);
		buffer.append("</fo:block>");

	}

	public void startBlock() {
		buffer.append("<fo:block >");

	}

	public void endBlock() {

		buffer.append("</fo:block>");

	}

	public void addtable(Table t) {
		buffer.append(t.getBuffer());
	}

	public InputStream getBufferInputStream() {
		return new ByteArrayInputStream(buffer.toString().getBytes());
	}

	public void addImage(String url) {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String urls = request.getRequestURL().toString();
		String baseURL = urls.substring(0, urls.length() - request.getRequestURI().length()) + request.getContextPath()
				+ "/";
		String full = baseURL + url;

		String s = "<fo:external-graphic  width=\"80pt\" content-height=\"80pt\""
				+ " content-width=\"80pt\"  src=\"url('" + full + "')\"/>";

		buffer.append("<fo:block >");
		buffer.append(s);
		buffer.append("</fo:block>");

//		buffer.append("<fo:block text-align=\"right\">");
//		buffer.append(TimeUtil.getTimestamp());
//		buffer.append("</fo:block>");

	}

	public void addImage(String url, int w, int h) {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String urls = request.getRequestURL().toString();
		String baseURL = urls.substring(0, urls.length() - request.getRequestURI().length()) + request.getContextPath()
				+ "/";
		String full = baseURL + url;

		String s = "<fo:external-graphic  width=\"" + w + "pt\" content-height=\"" + h + "pt\"" + " content-width=\""
				+ w + "pt\"  src=\"url('" + full + "')\"/>";

		buffer.append("<fo:block >");
		buffer.append(s);
		buffer.append("</fo:block>");

	}

	public void dump() {
		System.out.println(buffer.toString());
	}

	public void dump(String fileName) {
		try {
			try (PrintWriter pw = new PrintWriter(fileName);) {
				pw.print(buffer.toString());
				pw.flush();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addImage(byte[] photo) {
		try {
			File temp = File.createTempFile("img", ".jpg");
			try (FileOutputStream fos = new FileOutputStream(temp);) {

				fos.write(photo);
				fos.flush();
			}
			URL url = temp.toURI().toURL();

			buffer.append("<fo:block >");
			buffer.append("<fo:external-graphic src=\"" + url.toString() + "\"/>");

			buffer.append("</fo:block>");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String s[]) {
		PrintCreator prt = new PrintCreator();
		prt.insertStartDoc();
		prt.insertPageFormats();

		prt.startPageSequence(null);
		prt.addBlock("Elenco", "30pt");
		Table t = new Table();
		t.setHeader(false);
		t.addColumnDefinition(new Column("name", "2cm"));
		t.addColumnDefinition(new Column("cognome", "2cm"));

		for (int i = 0; i < 300; i++) {
			t.startRow();
			t.addDataCol("giovanni" + i);
			t.addDataCol("posa");
		}

		prt.addtable(t);
		prt.endPageSequence();
		prt.insertFineDoc();

		prt.dump();
	}

	public File convertToPDFNEWNoView(InputStream is) throws IOException, FOPException, TransformerException {
		// the XSL FO file

		// create an instance of fop factory
		FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
		// a user agent is needed for transformation
		FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
		// Setup output

		File tempPdf = TempFileFactory.getTempFile(".pdf");
		try (OutputStream out = new java.io.FileOutputStream(tempPdf);) {

			// Construct fop with desired output format
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

			// Setup XSLT
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();

			// Resulting SAX events (the generated FO) must be piped through to FOP
			Result res = new SAXResult(fop.getDefaultHandler());

			// Start XSLT transformation and FOP processing
			// That's where the XML is first transformed to XSL-FO and then
			// PDF is created
			Source src = new StreamSource(is);
			transformer.transform(src, res);
		}

		return tempPdf;

	}

	public File convertToPDFNEW(InputStream is) throws IOException, FOPException, TransformerException {
		// the XSL FO file

		// create an instance of fop factory
		FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
		// a user agent is needed for transformation
		FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
		// Setup output

		File tempPdf = TempFileFactory.getTempFile(".pdf");
		try (OutputStream out = new java.io.FileOutputStream(tempPdf);) {

			// Construct fop with desired output format
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

			// Setup XSLT
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();

			// Resulting SAX events (the generated FO) must be piped through to FOP
			Result res = new SAXResult(fop.getDefaultHandler());

			// Start XSLT transformation and FOP processing
			// That's where the XML is first transformed to XSL-FO and then
			// PDF is created
			Source src = new StreamSource(is);
			transformer.transform(src, res);
		}
		BasicDocumentViewController view = (BasicDocumentViewController) JsfUtil.getBean("basicDocumentViewController");
		view.setPdf(tempPdf);
		return tempPdf;
		// TempFileFactory.clean();
	}

	public List<Pair> caricaCampi(Object bean, Languages l) {
		List<Pair> lista = new ArrayList<Pair>();
		try {
			Field[] ll = bean.getClass().getDeclaredFields();
			for (Field f : ll) {
				String name = f.getName();
				String type = f.getType().getName();

				if (name.equals("n"))
					continue;
				if (!type.equals("long") && !type.equals("int") && !type.equals("java.lang.String")
						&& !type.equals("java.util.Date"))
					continue;

				String methodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
				try {
					Method meth = bean.getClass().getDeclaredMethod(methodName, null);

					Object val = meth.invoke(bean, null);

					if (val == null)
						val = "";
					if (val instanceof Date)
						val = TimeUtil.getCurrentDate((Date) val);
					String nameLoc = AnagraficaCampi.getLocalizedField(name, l);
					val = AnagraficaCampi.getLocalizedVal(name, val, l);

					lista.add(new Pair(nameLoc, val, type));
				} catch (NoSuchMethodException r) {
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return lista;
	}

	public List<Pair> caricaCampi(Object bean) {
		List<Pair> lista = new ArrayList<Pair>();
		try {
			Field[] ll = bean.getClass().getDeclaredFields();
			for (Field f : ll) {
				String name = f.getName();
				String type = f.getType().getName();

				if (name.equals("n"))
					continue;
				if (!type.equals("long") && !type.equals("int") && !type.equals("java.lang.String")
						&& !type.equals("java.util.Date"))
					continue;

				String methodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
				try {
					Method meth = bean.getClass().getDeclaredMethod(methodName, null);

					Object val = meth.invoke(bean, null);

					if (val == null)
						val = "";
					if (val instanceof Date)
						val = TimeUtil.getCurrentDate((Date) val);
					String nameLoc = AnagraficaCampi.getLocalizedField(name);
					val = AnagraficaCampi.getLocalizedVal(name, val);

					lista.add(new Pair(nameLoc, val, type));
				} catch (NoSuchMethodException r) {
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return lista;
	}

	public boolean isWithPageNumber() {
		return withPageNumber;
	}

	public void setWithPageNumber(boolean withPageNumber) {
		this.withPageNumber = withPageNumber;
	}

}
