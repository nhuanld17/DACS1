package TEST;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	public SendMail(String emailSender, String password, String receiver, String title, String content) throws UnsupportedEncodingException, MessagingException {
        Properties config = new Properties();
        config.setProperty("mail.smtp.host", "smtp.gmail.com");
        config.setProperty("mail.smtp.port", "465"); // Sử dụng cổng 465 cho SSL
        config.setProperty("mail.smtp.socketFactory.port", "465"); // Cổng này cũng cần được cài đặt cho SSL
        config.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        config.setProperty("mail.smtp.socketFactory.fallback", "false");
        config.setProperty("mail.smtp.auth", "true");
        
        Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailSender, password);
			}
		};
		
		String fromName = "Admin";
		String fromEmail = emailSender;
		String toEmail = receiver;
		String subject = title;
		String body = content;
		
		Session session = Session.getInstance(config, authenticator);
		MimeMessage mail = new MimeMessage(session);
		InternetAddress sender = new InternetAddress(fromEmail, fromName, "utf-8");
		
		mail.setFrom(sender);
		mail.setReplyTo(new InternetAddress[] {sender});
		mail.addRecipients(Message.RecipientType.BCC, toEmail);
		mail.setSubject(subject, "utf-8");
		mail.setContent(body,"text/html; charset=utf-8");
		mail.setSentDate(new Date());
		
		Transport.send(mail);
	}
}
