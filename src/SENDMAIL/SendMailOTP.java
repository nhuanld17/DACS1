package SENDMAIL;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailOTP {

	public SendMailOTP(String email, String OTP) {
        Properties config = new Properties();
        config.setProperty("mail.smtp.host", "smtp.gmail.com");
        config.setProperty("mail.smtp.port", "465"); // Sử dụng cổng 465 cho SSL
        config.setProperty("mail.smtp.socketFactory.port", "465"); // Cổng này cũng cần được cài đặt cho SSL
        config.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        config.setProperty("mail.smtp.socketFactory.fallback", "false");
        config.setProperty("mail.smtp.auth", "true"); // Xác thực bật
        
        Authenticator authenticator = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
                String email = "ledinhnhuan1917@gmail.com";
                String password = "rbxx lwir hhvu mgrz"; // Đảm bảo mật khẩu chính xác hoặc dùng App Password nếu có bật xác minh 2 bước
                return new PasswordAuthentication(email, password);
			}
        	
		};
		
		try {
			String fromName = "Admin";
			String fromEmail = "ledinhnhuan1917@gmail.com";
			String toEmail = email;
			String subject = "Mã khôi phục mật khẩu";
			String body = OTP;
			
			Session session = Session.getInstance(config, authenticator);
			MimeMessage mail = new MimeMessage(session);
			InternetAddress sender = new InternetAddress(fromEmail, fromName, "utf-8");
			
			mail.setFrom(sender);
			mail.setReplyTo(new InternetAddress[] {sender});
			mail.addRecipients(Message.RecipientType.TO, toEmail);
			mail.setSubject(subject, "utf-8");
			mail.setContent(body,"text/html; charset=utf-8");
			mail.setSentDate(new Date());
			
			Transport.send(mail);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
