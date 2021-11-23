import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryExecutor {

    public static ResultSet executeSelect(String selectQuery) {

        try {
            Connection connection = DatabaseDAO.connect();
            Statement statement = connection.createStatement();
            return statement.executeQuery(selectQuery);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String[] executeShowTables() {

        try {
            int i = 0;
            Connection connection = DatabaseDAO.connect();
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, "dziekanat",  "%", new String[] { "TABLE" });

            String[] tableHandler = new String[11];

            while(tables.next()) {
                tableHandler[i] = tables.getString("TABLE_NAME");
                i++;
            }

            return tableHandler;

        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }

    }

    public static List<String[]> executeToArray(String table, String schema) {

        try {
            Connection connection = DatabaseDAO.connect();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("Select * from " + schema + "." + table);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnNumber = rsmd.getColumnCount();


            List<String[]> tableList = new ArrayList<>();

            while(rs.next()) {
                String[] row = new String[columnNumber];
                for(int i = 1; i <= columnNumber; i++) {
                    Object obj = rs.getObject(i);
                    row[i-1] = (obj == null) ? null : obj.toString();
                }
                tableList.add(row);
            }

            return tableList;

        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }

    }

    public static void SendingEmailFromDatabaseAddress(String mail, String username, String password) throws MessagingException {

        List<String[]> tableList = executeToArray("mails", "roznosci");

        for (String[] row : tableList) {
            if (row[1].equals("true")) {

                Message message = new MimeMessage(DatabaseDAO.ConnectToMail(username, password));

                message.setFrom(new InternetAddress(mail));
                message.setRecipient(
                        Message.RecipientType.TO,
                        new InternetAddress(row[2])
                );
                message.setSubject("Testing Gmail Component");
                message.setText("Dzień Dobry!\n" + " z tej strony Dymański Bartosz, email wysłany automatycznie przez javaMail, jeśli otrzymałeś tę wiadomość, jesteś szczęsciarzem, ponieważ dałem ci wartość true w bazie danych.");

                Transport.send(message);

                System.out.println("Done");

            }
        }
    }

    public static void showArrayList(List<String[]> arrayList) {
        for(String[] row: arrayList) {
            for(String s: row){
                System.out.print( " " + s );
            }
            System.out.println();
        }
    }
}
