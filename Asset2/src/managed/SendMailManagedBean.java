package managed;

import java.io.File;
import java.util.List;

import beans.Utente;
import common.JsfUtil;
import database.dao.UtenteDAO;
import mail.MailSender;
import printcreator.NoCalendarDataException;
import printcreator.PrintCreatorSchedule;

public class SendMailManagedBean {
	private List<Utente> selectedUsers;
	private List<Utente> myList;

	public List<Utente> getAllUtenti() {

		UtenteDAO dao = new UtenteDAO();
		myList = dao.selectAll();
		return myList;
	}

	public void sendMail() {

		MailSender sender = MailSender.getSender();

		PrintCreatorSchedule pcs = (PrintCreatorSchedule) JsfUtil.getBean("printCreatorSchedule");
		try {
			String fileName = pcs.buildPDF();

			sender.send(fileName, selectedUsers, "Alca mail");

			JsfUtil.showMessage("Mail inviata");

			File f = new File(fileName);
			f.delete();
		} catch (NoCalendarDataException e) {
			
			e.printStackTrace();
		}
	}

	public List<Utente> getSelectedUsers() {
		return selectedUsers;
	}

	public void setSelectedUsers(List<Utente> selectedUsers) {
		this.selectedUsers = selectedUsers;
	}
}
