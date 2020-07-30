package za.co.virtualpostman.emailfilesender;

import com.sun.mail.smtp.SMTPTransport;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MessageUtils {
    private MessageUtils() {
    }

    public static void setEmailSubject(MimeMessage message) throws MessagingException {
        message.setSubject(message.getSubject());
    }

    public static void setEmailSubject(MimeMessage message, String subject) throws MessagingException {
        message.setSubject(subject);
    }

    public static void setFromEmail(MimeMessage message) throws MessagingException {
        message.setFrom(new InternetAddress(EmailFileSenderConstants.FROM_EMAIL_ADDRESS));
    }

    public static void setFromEmail(MimeMessage message, String fromEmail) throws MessagingException {
        message.setFrom(new InternetAddress(fromEmail));
    }

    public static void setRecipients(MimeMessage message) throws MessagingException {
        message.setRecipients(Message.RecipientType.TO, message.getRecipients(Message.RecipientType.TO));
    }

    public static void setRecipients(MimeMessage message, Address[] addresses) throws MessagingException {
        message.setRecipients(Message.RecipientType.TO, addresses);
    }

    public static void sendEmail(File emlFile, TransportDetails transportDetails) throws Exception {
        Session mailSession = MessageSession.getMailSession();
        MimeMessage message;
        try (InputStream source = new FileInputStream(emlFile)) {
            message = new MimeMessage(mailSession, source);
            MessageUtils.setFromEmail(message);
            MessageUtils.setRecipients(message);
            MessageUtils.setEmailSubject(message);
            sendEmailFile(emlFile, mailSession, message, transportDetails);
        }
    }

    public static void sendEmail(TransportDetails transportDetails, File emlFile, Address[] addresses, String subject)
            throws Exception {
        Session mailSession = MessageSession.getMailSession();
        MimeMessage message;
        try (InputStream source = new FileInputStream(emlFile)) {
            message = new MimeMessage(mailSession, source);
            MessageUtils.setFromEmail(message, transportDetails.getUser());
            MessageUtils.setRecipients(message, addresses);
            MessageUtils.setEmailSubject(message, subject);
            sendEmailFile(emlFile, mailSession, message, transportDetails);
        }
    }

    private static void sendEmailFile(File emlFile, Session mailSession, MimeMessage message, TransportDetails transportDetails)
            throws MessagingException {
        SMTPTransport transport = (SMTPTransport) mailSession.getTransport(EmailFileSenderConstants.SMTP_PROTOCOL);
        transport.connect(transportDetails.getHost(), transportDetails.getUser(), transportDetails.getPassword());
        System.out.println("Sending file: " + emlFile.getName());
        transport.sendMessage(message, message.getAllRecipients());
        System.out.println("File: " + emlFile.getName() + " sent successfully");
    }
}
