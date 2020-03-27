import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DBConnecter {

    /**
     * credit for creation and manipulation of the database
     * @author brie okeefe
     */

    protected Connection connection;

    /**
     * @author bob z
     * @author brie okeefe
     */
    DBConnecter(String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://dany.simmons.edu:3306/33501sp20_okeefebl?useUnicode=yes&characterEncoding=UTF-8",
                    user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author bob z
     * @author brie okeefe
     */
    public void displayInfo(){
        try {
            Statement selectArticle = connection.createStatement();
            ResultSet rs = selectArticle.executeQuery("SELECT TITLE, AUTHOR_FIRST, AUTHOR_LAST, ID," +
                    " POSTED FROM POSTINGS3");
            while (rs.next()) {
                System.out.println("ID: " + rs.getString(4)); // ID
                System.out.println("Title: " + rs.getString(1)); // Title
                System.out.println("Author: " + rs.getString(2) + " " + rs.getString(3)); // Author
                System.out.println("Published: " + rs.getString(5));
                System.out.println("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }

}
