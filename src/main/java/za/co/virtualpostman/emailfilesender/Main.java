package za.co.virtualpostman.emailfilesender;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        File curDir = new File(".");
        List<File> files = getAllFiles(curDir);
        TransportDetails transportDetails = TransportDetails.createTransportDetails(EmailFileSenderConstants.MAIL_HOST,
                EmailFileSenderConstants.FROM_EMAIL_ADDRESS, EmailFileSenderConstants.FROM_EMAIL_PASSWORD);
        for (File file : files){
            try {
                MessageUtils.sendEmail(file, transportDetails);
            }catch (Exception ex){
                throw new RuntimeException("Error sending file", ex);
            }
        }
    }

    private static List<File> getAllFiles(File curDir)
    {
        File[] filesList = curDir.listFiles();
        List<File> list = new LinkedList<>();
        for (File f : filesList){
            if (f.isFile()){
                if (f.getName().contains(".eml")){
                    list.add(f);
                }
            }
        }
        return list;
    }
}
