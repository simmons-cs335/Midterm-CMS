import java.util.Scanner;

/**
 * @author brie okeefe
 * @suthor richney c
 */

public class ContentRetrieval {
    public static void main(String[] args) {

        DBConnecter db = new DBConnecter("okeefebl", "1908035");
        Scanner input = new Scanner(System.in);

        /**
         // Program Menu
         // 1: search by title, author's last name, or ID
         // 2: display all
         // 3: more options includes: 1: check status 2: set status 3: check post time 3: format an article
         */

        System.out.println("------ MAIN MENU ------"+"\n");
        System.out.println("Please make a selection: " + "\n" + "\n" + "1: Search for an article " + "\n" + "2: Display all articles" + "\n" + "3: More options (Article ID required)");
        int userChoice = input.nextInt();
        if (userChoice == 1) {
            ArticleInfo.searchBy();
        } else if (userChoice == 2) {
            db.displayInfo();
        } else if (userChoice == 3) {
            System.out.println("Please enter the ID of the Article you would like to continue with.");
            int idPublisher = input.nextInt();
            ArticleInfo a = new ArticleInfo(idPublisher);
            ArticleInfo.publisher(a);
        } else {
            System.out.println("Invalid number. Please enter a number from 1-3:");
        }

        ArticleInfo.loopBack();

    }
}
