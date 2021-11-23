import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        try {
            DatabaseDAO.Odczyt();
            ResultSet result = QueryExecutor.executeSelect("Select * from dziekanat.studenci");

            while (result.next()) {
                int nrAlbumu = result.getInt("nr_albumu");
                String imie = result.getString("imie");
                String nazwisko = result.getString("nazwisko");

                System.out.print("Nr. Albumu: " + nrAlbumu + " ");
                System.out.print("Imie: " + imie + " ");
                System.out.println("Nazwisko: " + nazwisko + " ");

            }
            result.close();

            System.out.println("Nazwy tablic: ");
            System.out.println(Arrays.toString(QueryExecutor.executeShowTables()));

            System.out.println("Tablica dwuwymiarowa: ");
            QueryExecutor.showArrayList(QueryExecutor.executeToArray("mails", "roznosci"));

            QueryExecutor.SendingEmailFromDatabaseAddress("xxx", "xxx", "xxx");



        } catch (SQLException | FileNotFoundException | MessagingException e) {
            e.printStackTrace();
        }
    }
}
