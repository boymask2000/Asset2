package mail;

import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;

import beans.Utente;
import common.ApplicationConfig;

public abstract class MailSender {
	protected final static String MAILTYPE_GMAIL = "gmail";
	protected final static String MAILTYPE_LIBERO = "libero";
	protected final static String MAILTYPE_HMAIL = "hmail";


	protected static final String SMTP_USER = "smtp_user";
	protected static final String SMTP_PASSWORD = "smtp_password";
	protected static final String SMTP_PORT = "smtp_port";


	protected String smtpUser;
	protected String smtpPassword;

	protected boolean configOk = true;

	public MailSender(String mailType) {
	
		smtpUser = ApplicationConfig.getProperty(mailType + "." + SMTP_USER);
		smtpPassword = ApplicationConfig.getProperty(mailType + "." + SMTP_PASSWORD);
	}

	public static void main(String s[]) {
		Utente u = new Utente();
		u.setEmail("gposabella@libero.it");
		List<Utente> l = new ArrayList<Utente>();
		l.add(u);

		MailSender send = MailSender.getSender();
		send.send("/home/giovanni/Desktop/ALCA/Asset/FileInfoColori.pdf", l, "subject");
	}

	public static MailSender getSender() {
		String mailType = ApplicationConfig.getProperty("mailtype");
		String className = "mail.MailSender" + normalize(mailType);
		System.out.println(className);

		MailSender sender = null;
		try {
			sender = (MailSender) (Class.forName(className).newInstance());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {

			e.printStackTrace();
		}

		return sender;
	}

	private static String normalize(String mailType) {
		return mailType.substring(0, 1).toUpperCase() + mailType.substring(1);

	}

	public abstract void send(String fileName, List<Utente> destinatari, String subject) ;

	protected void attach(Multipart multipart, String fileName) throws MessagingException {
		MimeBodyPart messageBodyPart = new MimeBodyPart();

		DataSource source = new FileDataSource(fileName);
		messageBodyPart.setDataHandler(new DataHandler(source));
	//	int index = fileName.lastIndexOf("/");
		messageBodyPart.setFileName(fileName);
		multipart.addBodyPart(messageBodyPart);
	}

	protected String buildRecipients(List<Utente> destinatari) {
		String out = "";
		for (Utente u : destinatari) {
			if (out.length() > 0)
				out += ", ";
			out += u.getEmail();
		}
		System.out.println("DEST: " + out);
		return out;
	}

}
