import java.io.FileOutputStream;
import java.sql.*;
import java.util.Scanner;

public class ArticleInfo {

    static DBConnecter db = new DBConnecter("okeefebl", "1908035");

    private Connection connection;
    private int id;
    private String title;
    private String aFirst;
    private String aLast;
    private String photo;
    private byte[] postAt;
    private String posted;
    private String H1;
    private String P1;
    private String H2;
    private String P2;
    private String H3;
    private String P3;
    private String H4;
    private String P4;
    private String H5;
    private String P5;
    private String H6;
    private String P6;
    private String H7;
    private String P7;
    private String H8;
    private String P8;
    private String H9;
    private String P9;
    private String H10;
    private String P10;


    /**
     * @author brie okeefe
     * @author kaya m
     */
    // pulls data from db and stores in private variables
    public ArticleInfo(int i){
        connection =  db.getConnection();
        try {
            Statement selectInfo = connection.createStatement();
            ResultSet rs = selectInfo.executeQuery("SELECT ID, TITLE, AUTHOR_FIRST, AUTHOR_LAST," +
                    " PHOTO, POST_AT, POSTED, H1, P1, H2, P2, H3, P3, H4, P4, H5, P5, H6, P6, H7, P7," +
                    " H8, P8, H9, P9, H10, P10 FROM POSTINGS3 WHERE ID=" + i +";");
            while (rs.next()) {
                id = rs.getInt(1); // ID
                title = rs.getString(2); // Title
                aFirst = rs.getString(3); //AuthorFirst
                aLast = rs.getString(4); // AuthorLast
                photo = rs.getString(5);// Photo path
                postAt = rs.getBytes(6);
                posted = rs.getString(7);
                H1 = rs.getString(8);
                P1 = rs.getString(9);
                H2 = rs.getString(10);
                P2 = rs.getString(11);
                H3 = rs.getString(12);
                P3 = rs.getString(13);
                H4 = rs.getString(14);
                P4 = rs.getString(15);
                H5 = rs.getString(16);
                P5 = rs.getString(17);
                H6 = rs.getString(18);
                P6 = rs.getString(19);
                H7 = rs.getString(20);
                P7 = rs.getString(21);
                H8 = rs.getString(22);
                P8 = rs.getString(23);
                H9 = rs.getString(24);
                P9 = rs.getString(25);
                H10 = rs.getString(26);
                P10 = rs.getString(27);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author brie okeefe
     */
    public int getID(){
        return id;
    }

    /**
     * @author brie okeefe
     */
    public String getIDString(){
        return Integer.toString(id);
    }

    /**
     * @author brie okeefe
     */
    public String getTitle(){
        return title;
    }

    /**
     * @author brie okeefe
     */
    public String getAFirst(){
        return aFirst;
    }

    /**
     * @author brie okeefe
     */
    public String getALast(){
        return aLast;
    }

    /**
     * @author brie okeefe
     */
    public String getPhoto(){
        return photo;
    }

    /**
     * @author brie okeefe
     */
    public String[] getHeadings(){
        String [] headings = {H1, H2, H3, H4, H5, H6, H7, H8, H9, H10};
        return headings;
    }

    /**
     * @author brie okeefe
     */
    public String[] getParagraphs(){
        String[] paragraphs = {P1, P2,P3, P4, P5, P6, P7, P8, P9, P10};
        return paragraphs;
    }

    /**
     * @author brie okeefe
     */
    public String getBrowserAddress(){
        return "Y:\\ "+ this.getIDString() + ".html";
    }

    /**
     * @author brie okeefe
     */
    public String getPostAt(){
        String s = new String(postAt);
        return s;
    }

    /**
     * @author brie okeefe
     */
    //0000-00-00 00:00:00 time Stamp format
    // In progress
    public void setPostAt(String time, int id){
        byte[] timeBytes = time.getBytes();
        String sqlUpdate = "UPDATE POSTINGS3 SET POST_AT = "+timeBytes+" WHERE ID = " + id +";";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sqlUpdate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author brie okeefe
     */
    //Status refers to if the article has been posted or not
    public String getStatus(){ return posted; }

    public void setStatus(int i, int id){
        String sqlUpdate = "UPDATE POSTINGS3 SET POSTED = ? WHERE ID = ?;";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sqlUpdate);
            pstmt.setInt(1, i);
            pstmt.setInt(2, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author brie okeefe
     */
    /*
    //May use this later/ for final
    //public void printArticleInfo(){
    //    System.out.println("ID: " + getID());
    //    System.out.println("Title: "+getTitle());
    //    System.out.println("Name: " + getAFirst() + getALast());
    // }
    */

    /**
     * @author brie okeefe
     * @author richney c
     */
    public static void searchBy() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please select how you wish to search for articles: ");
        System.out.println("1: Author" + "\n" + "2: Title" + "\n" + "3: ID number" + "\n" + "4: Display all");
        boolean userInputIsValid = false;
        while (!userInputIsValid) {
            String userInput = input.nextLine();
            if (userInput.equals("1")) {
                userInputIsValid = true;
                byAuthor();
            } else if (userInput.equals("2")) {
                userInputIsValid = true;
                byTitle();
            } else if (userInput.equals("3")) {
                userInputIsValid = true;
                byID();
            } else if (userInput.equals("4")) {
                userInputIsValid = true;
                db.displayInfo();
            } else {
                System.out.println("Please type in a valid response.");
            }
        }
    }

    /**
     * @author richney c
     */
    public static void loopBack(){
        System.out.println("Return to Main Menu? yes or no");
        Scanner input = new Scanner(System.in);
        boolean response = false;
        while (!response){
            String userResponse = input.nextLine();
            if (userResponse.equals("yes")) {
                ContentRetrieval.main(null);
            }
                else{
                    System.exit(0);
                }
            }
        }

    /**
     * @author brie okeefe
     */
    // Searches DB for articles under ID
    public static void byID() {
        Scanner input = new Scanner(System.in);
        DBConnecter db = new DBConnecter("okeefebl", "1908035");
        Connection connection = db.getConnection();
        System.out.println("Please enter the ID for the article you are searching for:");
        String id = input.nextLine();
        System.out.print("Here are the articles by ID:" + "\n");
        try {
            Statement selectInfo = connection.createStatement();
            ResultSet rs = selectInfo.executeQuery("SELECT ID, TITLE, AUTHOR_FIRST, AUTHOR_LAST FROM POSTINGS3 WHERE ID = '" + id + "';");
            while (rs.next()) {
                System.out.println("ID: " + rs.getString(1)); // ID
                System.out.println("Title: " + rs.getString(2)); // Title
                System.out.println("Author: " + rs.getString(3) + " " + rs.getString(4));
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author brie okeefe
     */
    //Searches DB for articles under title
    public static void byTitle() {
        Scanner input = new Scanner(System.in);
        DBConnecter db = new DBConnecter("okeefebl", "1908035");
        Connection connection = db.getConnection();
        System.out.println("Please enter the title of the article you are searching for.");
        String title = input.nextLine();
        System.out.print("Here are the articles by title:"+ "\n");
        try {
            Statement selectInfo = connection.createStatement();
            ResultSet rs = selectInfo.executeQuery("SELECT ID, TITLE, AUTHOR_FIRST, AUTHOR_LAST" +
                    " FROM POSTINGS3 WHERE TITLE = '" + title + "';");
            while (rs.next()) {
                System.out.println("ID: " + rs.getString(1)); // ID
                System.out.println("Title: " + rs.getString(2)); // Title
                System.out.println("Author: " + rs.getString(3) + " " + rs.getString(4));
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author brie okeefe
     */
    //Searches DB for articles under Author
    public static void byAuthor() {
        Scanner input = new Scanner(System.in);
        DBConnecter db = new DBConnecter("okeefebl", "1908035");
        Connection connection = db.getConnection();
        System.out.println("Please enter the last name of the author you are searching for:");
        String aLastName = input.nextLine();
        System.out.print("Here are the articles by author:"+ "\n");
        try {
            Statement selectInfo = connection.createStatement();
            ResultSet rs = selectInfo.executeQuery("SELECT ID, TITLE FROM POSTINGS3 WHERE AUTHOR_LAST = '" + aLastName + "';");
            while (rs.next()) {
                System.out.println("ID: " + rs.getString(1)); // ID
                System.out.println("Title: " + rs.getString(2)); // Title
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author brie okeefe
     */
    public static void fileOutput(String htmlString, String id) {
        String fileID = id;
        try {
            FileOutputStream fileOut = new FileOutputStream("Y:\\ " + fileID + ".html");
            String s = htmlString;
            byte b[] = s.getBytes();//converting string into byte array
            fileOut.write(b);
            fileOut.close();
            System.out.println("success...");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * @author brie okeefe
     */
    public static void publisher(ArticleInfo ai) {
        FormatArticles fa = new FormatArticles();
        Scanner input = new Scanner(System.in);
        System.out.println("Please select what task you would like to accomplish with the selected article: ");
        System.out.println("1: Check status" + "\n" + "2: Set status" + "\n" + "3: Check post time" + "\n" + "4: Format an article" + "\n");
        boolean userInputIsValid = false;
        while (!userInputIsValid) {
            String userInput = input.nextLine();
            if (userInput.equals("1")) {
                userInputIsValid = true;
                System.out.println(ai.getStatus());
            } else if (userInput.equals("2")) {
                System.out.println("Has the article been posed yet? " + "\n" + "0: No" + "\n" + "1: Yes");
                int bool = input.nextInt();
                ai.setStatus(bool, ai.getID());
            } else if (userInput.equals("3")) {
                userInputIsValid = true;
                System.out.println("The new post time is set for" + ai.getPostAt());
            } else if (userInput.equals("4")) {
                userInputIsValid = true;
                fileOutput(fa.formatArticleAsHTML(ai), ai.getIDString());
                System.out.println("The HTML file should appear in your Y Drive");
                System.out.println("Or you can copy and paste this " + ai.getBrowserAddress() + " the URL bar");
            }
            //Make Option 4 if finished
            //System.out.println("When would you like this article posted?");
            //String time = input.nextLine();
            //ai.setPostTime(time, ai.getID());
            else {
                System.out.println("Please type in a valid response.");
            }
        }
    }
}

