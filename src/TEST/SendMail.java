package TEST;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {
    public SendMail(String emailSender, String password, String receiver, String title, String content, ArrayList<File> attachments) throws UnsupportedEncodingException, MessagingException {
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
        mail.setReplyTo(new InternetAddress[]{sender});
        mail.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        mail.setSubject(subject, "utf-8");

        // Create the message part 
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(body, "text/html; charset=utf-8");

        // Create a multipart message
        MimeMultipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        // Attach files if the attachments ArrayList is not null
        if (attachments != null) {
            for (File file : attachments) {
                MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(file);
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                attachmentBodyPart.setFileName(file.getName());
                multipart.addBodyPart(attachmentBodyPart);
            }
        }

        // Set the multipart message to the email
        mail.setContent(multipart);

        mail.setSentDate(new Date());

        Transport.send(mail);
    }
}
