package za.co.virtualpostman.emailfilesender;

import java.util.Properties;

public class PropertiesUtils {
    private PropertiesUtils() {
    }

    public static Properties getProperties() {
        Properties props = System.getProperties();
        props.put("mail.host", EmailFileSenderConstants.MAIL_HOST);
        props.put("mail.transport.protocol", EmailFileSenderConstants.SMTP_PROTOCOL);
        props.put("mail.smtp.port", EmailFileSenderConstants.SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        return props;
    }

}
