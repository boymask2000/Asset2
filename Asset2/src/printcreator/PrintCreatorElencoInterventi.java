package printcreator;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import beans.AssetAlca;
import beans.Check;
import beans.ChecklistIntervento;
import beans.FamigliaAsset;
import beans.Intervento;
import beans.Safety;
import common.Languages;
import common.Pair;
import common.TimeUtil;
import common.Util;
import database.dao.AssetAlcaDAO;
import database.dao.ChecklistInterventiDAO;
import database.dao.ChecksDAO;
import database.dao.FamigliaAssetDAO;
import database.dao.InterventiDAO;
import database.dao.SafetyDAO;

public class PrintCreatorElencoInterventi extends PrintCreator {

	private Languages language;

	public PrintCreatorElencoInterventi(Languages lang) {
		this.language = lang;

	}

	public File printInterventiDaA(String startDate, String endDate) {

		InterventiDAO intDAO = new InterventiDAO();
		List<Intervento> ints = intDAO.getInterventiFromTo(startDate, endDate);

		PrintCreator prt = new PrintCreator();
		prt.insertStartDoc();
		prt.insertPageFormats();

		for (Intervento ii : ints)
			printSingle(prt, ii);

		prt.insertFineDoc();

		File tempPdf = null;
		try (InputStream is = prt.getBufferInputStream();) {

			tempPdf = convertToPDFNEWNoView(is);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return tempPdf;
	}

	public void printSingle(PrintCreator prt, Intervento inte) {
		// ********************************PersonalData
		prt.startPageSequence(null);
		// prt.addImage("resources/images/alca.gif");
		// prt.dump();

		prt.addBlock("Info asset", "20pt");
		stampaInfoAsset(prt, inte);

		prt.endPageSequence();
		// ********************************
		prt.startPageSequence(null);
		// prt.addBlock("Intervento", "20pt");
		stampaIntervento(prt, inte);
		prt.endPageSequence();
//		// ********************************
//		prt.startPageSequence(null);
//		prt.addBlock("Interventi", "20pt");
//		stampaInterventi(prt, db);
//		prt.endPageSequence();
		// ********************************
	}

	public void stampaInfoAsset(PrintCreator prt, Intervento inte) {
		AssetAlcaDAO assetDAO = new AssetAlcaDAO();
		AssetAlca asset = assetDAO.searchById(inte.getAssetId());

		Table t = new Table();
		t.setHeader(false);
		t.addColumnDefinition(new Column("", "6cm"));
		t.addColumnDefinition(new Column("", "12cm"));

		List<Pair> lista = caricaCampi(asset,language);
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
			if (language.getLanguage().equalsIgnoreCase("en")) {
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
		prt.addBlock(Util.getLocalizedString("INTERVENTO", language.getLocale()) + " "
				+ TimeUtil.getFormattedDate(inter.getData_pianificata(), language), "18pt");
		Table t = new Table();
		t.setHeader(false);
		t.addColumnDefinition(new Column("", "6cm"));
		t.addColumnDefinition(new Column("", "4cm"));

		List<Pair> lista = caricaCampi(inter, language);
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
				t.addDataCol(Util.getLocalizedString("CODICE_NORMATIVA", language.getLocale()) + ":");
				t.addDataCol(check.getCodiceNormativa());

				PrintCommon.stampaNormativa(t, check.getCodiceNormativa(), language);

				t.startRow();
				t.addDataCol(Util.getLocalizedString("DESCRIZIONE", language.getLocale()) + ":");
				if (language.getLanguage().equalsIgnoreCase("en"))
					t.addDataCol(cleanTextForSpecialChars(check.getDescriptionUS()));
				else
					t.addDataCol(cleanTextForSpecialChars(check.getDescription()));
				prt.addtable(t);

			}
		}
		if (inter.getData_effettiva() != null) {
			PrintCommon.stampaEsiti(prt, inter, language.getLocale());
			PrintCommon.stampaCommento(prt, inter, language.getLocale());
		}
	//	PrintCommon.stampaFoto(prt, inter.getId());
		// prt.addtable(t);

	}

}
