package printcreator;

import java.io.InputStream;
import java.util.List;

import beans.AssetAlca;
import beans.Intervento;
import common.JsfUtil;
import common.Pair;
import database.dao.InterventiDAO;
import managed.ManagedAssetBean;

public class PrintCreatorFullDettaglioAsset extends PrintCommon {

	public String printFullAsset() {

		ManagedAssetBean db = (ManagedAssetBean) JsfUtil.getBean("managedAssetBean");

		// Asset asset = db.getSelectedAsset();

		PrintCreator prt = new PrintCreator();
		prt.insertStartDoc();
		prt.insertPageFormats();

		// ********************************PersonalData
		prt.startPageSequence(null);
		prt.addImage("resources/images/alca.gif");
		prt.dump();

		prt.addBlock("Info asset", "20pt");
		stampaMainData(prt, db);

		prt.endPageSequence();
		// ********************************
		prt.startPageSequence(null);
		prt.addBlock("Ultimo aggiornamento", "20pt");
		stampaUltimoIntervento(prt, db);
		prt.endPageSequence();
		// ********************************
		prt.startPageSequence(null);
		prt.addBlock("Interventi", "20pt");
		stampaInterventi(prt, db);
		prt.endPageSequence();
		// ********************************

		prt.insertFineDoc();

		try (InputStream is = prt.getBufferInputStream();) {
			convertToPDFNEW(is);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "viewFile";
	}

	private void stampaUltimoIntervento(PrintCreator prt, ManagedAssetBean db) {
		AssetAlca asset = db.getSelectedAsset();
		InterventiDAO dao = new InterventiDAO();
		Intervento lastInter = dao.getUltimoInterventoFatto(asset.getId());
		if (lastInter != null) {
			stampaIntervento(prt, lastInter);
		}
	}

	private void stampaInterventi(PrintCreator prt, ManagedAssetBean db) {

		AssetAlca asset = db.getSelectedAsset();
		InterventiDAO dao = new InterventiDAO();
		List<Intervento> li = dao.getInterventiForAsset(asset.getId());
		for (Intervento inter : li) {
			stampaIntervento(prt, inter);
		}

	}

	private void stampaMainData(PrintCreator prt, ManagedAssetBean bean) {

		AssetAlca asset = bean.getSelectedAsset();

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


}
