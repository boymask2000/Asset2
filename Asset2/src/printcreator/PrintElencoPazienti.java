package printcreator;

import java.io.InputStream;
import java.util.List;

import beans.Utente;
import common.JsfUtil;
import managed.ManagedUtentiBean;

public class PrintElencoPazienti extends PrintCreator {
	public String convertElencoPazientiToPDF() {

		ManagedUtentiBean db = (ManagedUtentiBean) JsfUtil.getBean("managedUtentiBean");

		PrintCreator prt = new PrintCreator();
		prt.insertStartDoc();
		prt.insertPageFormats();

		prt.startPageSequence(null);
		prt.addBlock("Selection: " + db.getSelectedUser(), "30pt");
		Table t = new Table();
		t.addColumnDefinition(new Column("", "6cm"));
		t.addColumnDefinition(new Column("", "4cm"));

		List<Utente> lista = db.getAllUtenti();

		for (Utente p : lista) {
			t.startRow();
			t.addDataCol(p.getUsername());

		}

		prt.addtable(t);
		prt.endPageSequence();
		prt.insertFineDoc();

		try (InputStream is = prt.getBufferInputStream();) {

			convertToPDFNEW(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JsfUtil.goTo("stampa");
		return "";
	}
}
