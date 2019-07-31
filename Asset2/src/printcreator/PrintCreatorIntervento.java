package printcreator;

import java.io.InputStream;

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

}
