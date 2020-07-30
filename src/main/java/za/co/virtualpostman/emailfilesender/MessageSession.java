package za.co.virtualpostman.emailfilesender;

import javax.mail.Session;

public class MessageSession {

    private MessageSession() {
    }

    public static Session getMailSession() {
        return Session.getDefaultInstance(PropertiesUtils.getProperties(), null);
    }

}
