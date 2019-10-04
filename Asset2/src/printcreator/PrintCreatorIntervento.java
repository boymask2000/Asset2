package printcreator;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import common.JsfUtil;
import managed.ManagedInterventiBean;

public class PrintCreatorIntervento extends PrintCommon {

	public String printIntervento() {

		ManagedInterventiBean db = (ManagedInterventiBean) JsfUtil.getBean("managedInterventiBean");
		PrintCreatorFullDettaglioInterventi pc = (PrintCreatorFullDettaglioInterventi) JsfUtil
				.getBean("printCreatorFullDettaglioInterventi");

		// Asset asset = db.getSelectedAsset();

		PrintCreator prt = new PrintCreator();
		prt.insertStartDoc();
		prt.insertPageFormats();

		pc.printSingle(prt, db.getSelectedIntevento());

		prt.insertFineDoc();

		try (InputStream is = prt.getBufferInputStream();) {
			convertToPDFNEW(is);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "viewFile";
	}

	private File buildPdf() {
		ManagedInterventiBean db = (ManagedInterventiBean) JsfUtil.getBean("managedInterventiBean");
		PrintCreatorFullDettaglioInterventi pc = (PrintCreatorFullDettaglioInterventi) JsfUtil
				.getBean("printCreatorFullDettaglioInterventi");

		// Asset asset = db.getSelectedAsset();

		PrintCreator prt = new PrintCreator();
		prt.insertStartDoc();
		prt.insertPageFormats();

		pc.printSingle(prt, db.getSelectedIntevento());

		prt.insertFineDoc();

		try (InputStream is = prt.getBufferInputStream();) {
			return convertToPDFNEW(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String printIntervento2() {
		File file = buildPdf();

		final int DEFAULT_BUFFER_SIZE = 10240;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

		// File file = new File(getFilePath(), getFileName());
		// BufferedInputStream input = null;
		//BufferedOutputStream output = null;

		try {
			// Open file.
			try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(file), DEFAULT_BUFFER_SIZE);) {

				// Init servlet response.
				response.reset();
				response.setContentType("application/pdf");
				response.setContentLength((int) file.length());
				response.setHeader("Content-disposition", "inline; filename=\"" + file.getAbsolutePath() + "\"");
				try (BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream(),
						DEFAULT_BUFFER_SIZE);) {

					// Write file contents to response.
					byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
					int length; 
					while ((length = input.read(buffer)) > 0) {
						output.write(buffer, 0, length);
					}

					// Finalize task.
					output.flush();
				}
			}
		} catch(Exception e) {
e.printStackTrace();
		}

		// Inform JSF that it doesn't need to handle response.
		// This is very important, otherwise you will get the following exception in the
		// logs:
		// java.lang.IllegalStateException: Cannot forward after response has been
		// committed.
		facesContext.responseComplete();
		return "";
	}

}
