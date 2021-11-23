import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class DatabaseDAO {

    private static String url;
    private static String user;
    private static String password;

    public static Session ConnectToMail(String username, String password) {

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        return Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

    public static void Odczyt() throws FileNotFoundException {

            File file = new File("dane.txt");
            Scanner in = new Scanner(file);

            for(int i = 0; i < 3; i++) {
                if(i == 0) {
                    url = in.nextLine();
                } else if (i == 1) {
                    user = in.nextLine();
                } else {
                    password = in.nextLine();
                }
            }
        }

    public static Connection connect() {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
