package printcreator;

import java.io.InputStream;
import java.util.List;

import beans.Intervento;
import common.JsfUtil;
import managed.ManagedReportInterventiBean;

public class PrintCreatorFullDettaglioInterventi extends PrintCommon {
	
//	public PrintCreatorFullDettaglioInterventi() {
//		
//		System.out.println("##############################################");
//		ManagedReportInterventiBean mrib = (ManagedReportInterventiBean) JsfUtil.getBean("managedReportInterventiBean");
//		System.out.println(mrib);
//	}
//
//
//	

	public String printAll() {

		ManagedReportInterventiBean mrib = (ManagedReportInterventiBean) JsfUtil.getBean("managedReportInterventiBean");

		List<Intervento> lista = mrib.getInterventi();

		// Asset asset = db.getSelectedAsset();

		PrintCreator prt = new PrintCreator();
		prt.insertStartDoc();
		prt.insertPageFormats();

		for (Intervento ii : lista)
			printSingle(prt, ii);

		prt.insertFineDoc();

		try (InputStream is = prt.getBufferInputStream();) {
			convertToPDFNEW(is);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "viewFile";
	}

	public void printSingle(PrintCreator prt, Intervento inte) {
		// ********************************PersonalData
		prt.startPageSequence(null);
		prt.addImage("resources/images/alca.gif");
	//	prt.dump();

		prt.addBlock("Info asset", "20pt");
		stampaInfoAsset(prt, inte);

		prt.endPageSequence();
		// ********************************
		prt.startPageSequence(null);
	//	prt.addBlock("Intervento", "20pt");
		stampaIntervento(prt, inte);
		prt.endPageSequence();
//		// ********************************
//		prt.startPageSequence(null);
//		prt.addBlock("Interventi", "20pt");
//		stampaInterventi(prt, db);
//		prt.endPageSequence();
		// ********************************
	}




}
