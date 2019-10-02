package managed;

import java.util.List;

import beans.MailSender;
import beans.Utente;
import common.JsfUtil;
import database.dao.UtenteDAO;
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

		MailSender sender = new MailSender();
		
		PrintCreatorSchedule pcs = (PrintCreatorSchedule)JsfUtil.getBean("printCreatorSchedule");
		String fileName = pcs.buildPDF();
System.out.println(fileName);
		sender.send(fileName, selectedUsers, "Alca mail");
	}

	public List<Utente> getSelectedUsers() {
		return selectedUsers;
	}

	public void setSelectedUsers(List<Utente> selectedUsers) {
		this.selectedUsers = selectedUsers;
	}
}
