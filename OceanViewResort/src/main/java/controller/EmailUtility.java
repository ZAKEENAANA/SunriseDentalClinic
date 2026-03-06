package controller;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.io.File;

public class EmailUtility {

    public static void sendEmailWithAttachment(String toEmail, String subject, String body, File attachment) {
        final String fromEmail = "hasnizakee20@gmail.com"; // change
        final String password = "ntid cjyj pdtz vuvg";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            msg.setSubject(subject);

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            MimeBodyPart attachPart = new MimeBodyPart();
            attachPart.attachFile(attachment);
            multipart.addBodyPart(attachPart);

            msg.setContent(multipart);

            Transport.send(msg);
            System.out.println("Email sent to " + toEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}