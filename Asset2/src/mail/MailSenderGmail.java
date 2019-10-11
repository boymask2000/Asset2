package mail;

import java.util.List;
import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
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

import beans.Utente;

public class MailSenderGmail extends MailSender {

	public MailSenderGmail() {
		super(MAILTYPE_GMAIL);

	}

	public void send(String fileName, List<Utente> destinatari, String subject) {
		if (!configOk)
			return;

		if (destinatari == null || destinatari.size() == 0)
			return;

		Properties props = new Properties();

		props.put("mail.smtp.host", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(smtpUser, smtpPassword);
			}
		});

		// session.setDebug(true); // Enable the debug mode

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(smtpUser));
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

		} catch (AuthenticationFailedException y) {
			y.printStackTrace();

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}
