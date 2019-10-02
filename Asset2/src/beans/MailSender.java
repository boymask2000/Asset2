package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sun.mail.smtp.SMTPTransport;

import common.ApplicationConfig;
import common.JsfUtil;

public class MailSender {
	private static final String SMTP_SERVER = "smtp_server";
	private static final String SMTP_USER = "smtp_user";
	private static final String SMTP_PASSWORD = "smtp_password";
	private static final String SMTP_PORT = "smtp_port";

	private String smtpServer;
	private String smtpUser;
	private String smtpPassword;
	private String smtpPort = "25";
	private boolean configOk = true;

	public MailSender() {
		smtpServer = ApplicationConfig.getProperty(SMTP_SERVER);
		smtpUser = ApplicationConfig.getProperty(SMTP_USER);
		smtpPassword = ApplicationConfig.getProperty(SMTP_PASSWORD);
		String s_smtpPort = ApplicationConfig.getProperty(SMTP_PORT);
		if (s_smtpPort != null) {

			try {
				Integer.parseInt(s_smtpPort);
				smtpPort = s_smtpPort;
			} catch (NumberFormatException e) {

			}
		}
		System.out.println(smtpServer);
		System.out.println(smtpUser);
		System.out.println(smtpPassword);
		if (smtpServer == null) {
			JsfUtil.showMessage("Definire smtp server in configurazione");
			System.out.println(SMTP_SERVER);
			System.out.println(SMTP_USER);
			System.out.println(SMTP_PASSWORD);
			configOk = false;
		}
	}

	public static void main(String s[]) {
		Utente u = new Utente();
		u.setEmail("gposabella@libero.it");
		List<Utente> l = new ArrayList<Utente>();
		l.add(u);

		MailSender send = new MailSender();
		send.send("/home/giovanni/Desktop/ALCA/Asset/FileInfoColori.pdf", l, "subject");
	}

	public void send(String fileName, List<Utente> destinatari, String subject) {
		if (!configOk)
			return;

		if (destinatari == null || destinatari.size() == 0)
			return;

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.libero.it"); // for gmail use smtp.gmail.com
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		// props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(smtpUser, smtpPassword);
			}
		});

		// session.setDebug(true); // Enable the debug mode

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("gposabella@libero.it"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(buildRecipients(destinatari)));
			message.setSubject(subject);

			String msgg = "";

			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(msgg, "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);

			attach(multipart, fileName);

			message.setContent(multipart);

			SMTPTransport.send(message);

		} catch (AuthenticationFailedException auth) {
			auth.printStackTrace();

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	private void attach(Multipart multipart, String fileName) throws MessagingException {
		MimeBodyPart messageBodyPart = new MimeBodyPart();

		DataSource source = new FileDataSource(fileName);
		messageBodyPart.setDataHandler(new DataHandler(source));
		int index=fileName.lastIndexOf("/");
		messageBodyPart.setFileName(fileName);
		multipart.addBodyPart(messageBodyPart);
	}

	private String buildRecipients(List<Utente> destinatari) {
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
