package printcreator;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import beans.Asset;
import common.JsfUtil;
import common.Pair;
import managed.BasicDocumentViewController;
import managed.ManagedAssetBean;

public class PrintCreatorFullDettaglioAsset extends PrintCreator {

	public String printFullAsset() {

		ManagedAssetBean db = (ManagedAssetBean) JsfUtil.getBean("managedAssetBean");

		// Asset asset = db.getSelectedAsset();

		PrintCreator prt = new PrintCreator();
		prt.insertStartDoc();
		prt.insertPageFormats();

		// ********************************PersonalData
		prt.startPageSequence(null);
		prt.addBlock("Personal Data", "30pt");
		stampaMainData(prt, db);
		prt.endPageSequence();
		// ********************************

		prt.insertFineDoc();

		InputStream is = prt.getBufferInputStream();
		try {
			convertToPDFNEW(is);
		} catch (Exception e) {
			e.printStackTrace();
		}

	//	JsfUtil.goTo("stampa");

		return "viewFile";
	}

	private void stampaMainData(PrintCreator prt, ManagedAssetBean bean) {

		Asset asset = bean.getSelectedAsset();

		Table t = new Table();
		t.setHeader(false);
		t.addColumnDefinition(new Column("", "6cm"));
		t.addColumnDefinition(new Column("", "4cm"));

		List<Pair> lista = caricaCampi(asset);
		for (Pair p : lista) {
			t.startRow();
			t.addDataCol(p.getName() + ":");
			t.addDataCol("" + p.getVal());
		}

		prt.addtable(t);
	}

//	private String toPlainText(String s) {
//		String out ="<![CDATA[";
//		out +=s;
//		out+="]]>";
//		return out;
//	}
//	private String toPlainText2(String s) {
//		String out ="&lt;![CDATA[";
//		out +=s;
//		out+="]]&gt;";
//		return out;
//	}

//	private int getSize(Pair p) {
//		int size = (int) (p.getName().length() * 0.3);
//		if (size < 1)
//			size = 1;
//		if (p.getType().equals("java.util.Date"))
//			size = 2;
//		if (size > 2)
//			size = 2;
//		return size;
//	}
//

}
