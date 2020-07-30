package za.co.virtualpostman.emailfilesender;

public class TransportDetails {
    private final String user;
    private final String password;
    private final String host;

    private TransportDetails(String host, String user, String password) {
        this.host = host;
        this.user = user;
        this.password = password;
    }

    public static TransportDetails createTransportDetails(String host, String user, String password) {
        return new TransportDetails(host, user, password);
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getHost() {
        return host;
    }
}
