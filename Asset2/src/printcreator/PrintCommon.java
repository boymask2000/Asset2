package printcreator;

import java.util.List;

import beans.AssetAlca;
import beans.Check;
import beans.ChecklistIntervento;
import beans.FamigliaAsset;
import beans.Intervento;
import beans.Normativa;
import beans.Safety;
import beans.Status;
import common.JsfUtil;
import common.Pair;
import common.TimeUtil;
import common.Util;
import database.dao.AssetAlcaDAO;
import database.dao.ChecklistInterventiDAO;
import database.dao.ChecksDAO;
import database.dao.FamigliaAssetDAO;
import database.dao.NormativeDAO;
import database.dao.SafetyDAO;
import managed.LanguageBean;

public class PrintCommon extends PrintCreator {
	protected String locale;

	public PrintCommon() {
		LanguageBean bean = (LanguageBean) JsfUtil.getBean("language");
		this.locale = bean.getCurrentLocale();
	}

	public void stampaInfoAsset(PrintCreator prt, Intervento inte) {
		AssetAlcaDAO assetDAO = new AssetAlcaDAO();
		AssetAlca asset = assetDAO.searchById(inte.getAssetId());

		Table t = new Table();
		t.setHeader(false);
		t.addColumnDefinition(new Column("", "6cm"));
		t.addColumnDefinition(new Column("", "12cm"));

		List<Pair> lista = caricaCampi(asset);
		for (Pair p : lista) {
			t.startRow();
			t.addDataCol(p.getName() + ":");
			t.addDataCol("" + p.getVal());
		}

		prt.addtable(t);

		stampaSafety(prt, asset);

	}

	public void stampaSafety(PrintCreator prt, AssetAlca asset) {
		FamigliaAssetDAO fad = new FamigliaAssetDAO();
		FamigliaAsset fam = fad.searchByName(asset.getFacSystem());
		int count = 1;
		SafetyDAO dao = new SafetyDAO();
		List<Safety> safList = dao.selectByFamily(fam.getId());
		prt.addBlock("Checklist", "18pt");
		for (Safety saf : safList) {
			prt.addBlock("" + (count++), "16pt");
			Table t = new Table();
			t.setHeader(false);
			t.addColumnDefinition(new Column("", "3cm"));
			t.addColumnDefinition(new Column("", "12cm"));
			t.startRow();
			if (locale.equalsIgnoreCase("en")) {
				t.addDataCol("Risk:");
				t.addDataCol(saf.getRisk_en());
				t.startRow();
				t.addDataCol("PPE:");
				t.addDataCol(saf.getPpe_en());
			} else {
				t.addDataCol("Risk:");
				t.addDataCol(saf.getRisk_it());
				t.startRow();
				t.addDataCol("PPE:");
				t.addDataCol(saf.getPpe_it());
			}
			prt.addtable(t);
		}

	}

	public void stampaIntervento(PrintCreator prt, Intervento inter) {
		prt.addBlock(
				Util.getLocalizedString("INTERVENTO") + " " + TimeUtil.getFormattedDate(inter.getData_pianificata()),
				"18pt");
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
		prt.addBlock("Checklist", "18pt");
//		t = new Table();
//		t.setHeader(false);
//		t.addColumnDefinition(new Column("", "6cm"));
//		t.addColumnDefinition(new Column("", "4cm"));

		ChecklistInterventiDAO cid = new ChecklistInterventiDAO();
		List<ChecklistIntervento> cli = cid.getCheckListForIntervento(inter);
		int count = 1;
		for (ChecklistIntervento cl : cli) {
			ChecksDAO cd = new ChecksDAO();
			List<Check> checks = cd.getChecksByID(cl.getCheckId());

			for (Check check : checks) {
				prt.addBlock("" + (count++), "16pt");

				t = new Table();
				t.setHeader(false);
				t.addColumnDefinition(new Column("", "4cm"));
				t.addColumnDefinition(new Column("", "12cm"));

				t.startRow();
				t.addDataCol(Util.getLocalizedString("CODICE_NORMATIVA") + ":");
				t.addDataCol(check.getCodiceNormativa());

				stampaNormativa(t, check.getCodiceNormativa());

				t.startRow();
				t.addDataCol(Util.getLocalizedString("DESCRIZIONE") + ":");
				if (locale.equalsIgnoreCase("en"))
					t.addDataCol(check.getDescriptionUS());
				else
					t.addDataCol(check.getDescription());
				prt.addtable(t);

			}
		}
		if (inter.getData_effettiva() != null) {
			stampaEsiti(prt, inter);
			stampaCommento(prt, inter);
		}
		// prt.addtable(t);

	}

	private static void stampaEsiti(PrintCreator prt, Intervento inter) {
		prt.addBlock(Util.getLocalizedString("ESITO"), "16pt");
		Table t = new Table();
		t.setHeader(false);
		t.addColumnDefinition(new Column("", "6cm"));
		t.addColumnDefinition(new Column("", "6cm"));
		t.addColumnDefinition(new Column("", "6cm"));
		t.startRow();
		if (inter.getEsito() == 1)
			t.addDataCol("X");
		else
			t.addDataCol(".");
		t.setAlign("center");
		t.setBackgroundColor(Status.COL1);
		if (inter.getEsito() == 2)
			t.addDataCol("X");
		else
			t.addDataCol(" ");
		t.setAlign("center");
		t.setBackgroundColor(Status.COL2);
		if (inter.getEsito() == 3)
			t.addDataCol("X");
		else
			t.addDataCol(" ");
		t.setAlign("center");
		t.setBackgroundColor(Status.COL3);
		t.startRow();
		if (inter.getEsito() == 4)
			t.addDataCol("X");
		else
			t.addDataCol(".");
		t.setAlign("center");
		t.setBackgroundColor(Status.COL4);
		if (inter.getEsito() == 5)
			t.addDataCol("X");
		else
			t.addDataCol("");
		t.setAlign("center");
		t.setBackgroundColor(Status.COL5);
		if (inter.getEsito() == 6)
			t.addDataCol("X");
		else
			t.addDataCol("");
		t.setAlign("center");
		t.setBackgroundColor(Status.COL6);
		t.startRow();
		if (inter.getEsito() == 7)
			t.addDataCol("X");
		else
			t.addDataCol(".");
		t.setAlign("center");
		t.setBackgroundColor(Status.COL7);
		if (inter.getEsito() == 8)
			t.addDataCol("X");
		else
			t.addDataCol("");
		t.setAlign("center");
		t.setBackgroundColor(Status.COL8);
		if (inter.getEsito() == 9)
			t.addDataCol("X");
		else
			t.addDataCol("");
		t.setAlign("center");
		t.setBackgroundColor(Status.COL9);
		prt.addtable(t);

	}

	private static void stampaCommento(PrintCreator prt, Intervento inter) {
		prt.addBlock(Util.getLocalizedString("COMMENTO") + ":", "16pt");
		Table t1 = new Table();
		t1.setHeader(false);
		t1.addColumnDefinition(new Column("", "18cm"));

		t1.startRow();

		if (inter.getCommento() == null)
			t1.addDataCol(".");
		else
			t1.addDataCol(inter.getCommento());
		prt.addtable(t1);
	}

	private static void stampaNormativa(Table t, String cod) {
		NormativeDAO dao = new NormativeDAO();
		Normativa nor = dao.getNormativaPerCodice(cod);
		t.startRow();
		t.addDataCol("Frequenza:");
		t.addDataCol(nor.getFrequenza());

	}

}
