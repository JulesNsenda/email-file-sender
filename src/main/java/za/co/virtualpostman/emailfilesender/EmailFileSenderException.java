package za.co.virtualpostman.emailfilesender;

public class EmailFileSenderException
        extends Exception
{
    public EmailFileSenderException()
    {
    }

    public EmailFileSenderException(String message)
    {
        super(message);
    }

    public EmailFileSenderException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
