package printcreator;

import java.io.InputStream;
import java.util.List;

import beans.Asset;
import beans.Intervento;
import common.JsfUtil;
import common.Pair;
import database.dao.InterventiDAO;
import managed.ManagedAssetBean;

public class PrintCreatorFullDettaglioAsset extends PrintCreator {



	public String printFullAsset() {

		ManagedAssetBean db = (ManagedAssetBean) JsfUtil.getBean("managedAssetBean");

		// Asset asset = db.getSelectedAsset();

		PrintCreator prt = new PrintCreator();
		prt.insertStartDoc();
		prt.insertPageFormats();
		
		// ********************************PersonalData
	//	prt.addImage("resources/images/alca.gif");
		prt.startPageSequence(null);
		prt.addBlock("Info asset", "20pt");
		stampaMainData(prt, db);
		
		prt.endPageSequence();
		// ********************************
		prt.startPageSequence(null);
		prt.addBlock("Ultimo aggiornamento", "20pt");
		stampaUltimoIntervento(prt,db);
		prt.endPageSequence();
		// ********************************
		prt.startPageSequence(null);
		prt.addBlock("Interventi", "20pt");
		stampaInterventi(prt, db);
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

	private void stampaUltimoIntervento(PrintCreator prt, ManagedAssetBean db) {
		Asset asset = db.getSelectedAsset();
		InterventiDAO dao = new InterventiDAO();
		Intervento lastInter = dao.getUltimoInterventoFatto(asset.getId());
		Table t = new Table();
		t.setHeader(false);
		t.addColumnDefinition(new Column("", "6cm"));
		t.addColumnDefinition(new Column("", "4cm"));
		t.startRow();
		t.addDataCol("Data ultimo intervento:");
		t.addDataCol("" + lastInter.getData_effettiva());
		t.startRow();
		t.addDataCol("Esito:");
		t.addDataCol("" + lastInter.getEsito());
		
		prt.addtable(t);
	}

	private void stampaInterventi(PrintCreator prt, ManagedAssetBean db) {

		Asset asset = db.getSelectedAsset();
		InterventiDAO dao = new InterventiDAO();
		List<Intervento> li = dao.getInterventiForAsset(asset.getId());
		for( Intervento inter: li) {
			Table t = new Table();
			t.setHeader(false);
			t.addColumnDefinition(new Column("", "6cm"));
			t.addColumnDefinition(new Column("", "4cm"));
			
			List<Pair> lista = caricaCampi(inter);
			for (Pair p : lista) {
				t.startRow();
				t.addDataCol(p.getName() + ":");
				t.addDataCol("" + p.getVal());
			}
			prt.addtable(t);
		}
		
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
