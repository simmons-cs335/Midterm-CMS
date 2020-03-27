public class FormatArticles {

    /**
     * credit for the css file sheet used in this class
     * @author kaya m
     */

    String htmlHead = " ";
    String htmlHeader = " ";
    String htmlBody = " ";
    String htmlArticle = " ";

    /**
     * @author brie okeefe
     * @author kaya m
     */
    //format Article as HTML
    public String formatArticleAsHTML(ArticleInfo article) {
        formatHeadAsHTML(article);
        formatHeaderAsHTML(article);
        formatBodyAsHTML(article);
        htmlArticle += htmlHead;
        htmlArticle += htmlHeader;
        htmlArticle += htmlBody;
        return htmlArticle;
    }

    /**
     * @author brie okeefe
     * @author kaya m
     */
    //format Head as HTML
    public String formatHeadAsHTML(ArticleInfo article) {
        htmlHead = "<!DOCTYPE html> <html lang='en'>";
        htmlHead += "<head>";
        htmlHead += "<title>" + article.getTitle() + "</title>";
        htmlHead += "<link rel='stylesheet' href='styles.css' media='screen'> <link href=\"https://fonts.googleapis.com/css?family=Khand:700|Lora|Raleway&display=swap\" rel=\"stylesheet\">";
        htmlHead += "</head>";
        return htmlHead;
    }

    /**
     * @author brie okeefe
     * @author kaya m
     */
    //format Header as HTML
    public String formatHeaderAsHTML(ArticleInfo article) {
        htmlHeader += "<div class='container'>";
        htmlHeader += "<header> ";
        htmlHeader += "<h1> " + article.getTitle() + " </h1>";
        htmlHeader += " <p class=\"submitted-by\">Submitted by " + article.getAFirst() + " ";
        htmlHeader += article.getALast() + " </p>";
        htmlHeader += " <img src='" + article.getPhoto() + "' height = '300' width = '300'> ";
        return htmlHeader;
    }

    /**
     * @author brie okeefe
     * @author kaya m
     */
    // Format Body as html
    public String formatBodyAsHTML(ArticleInfo article) {
        String [] headings = article.getHeadings();
        String[] paragraphs = article.getParagraphs();
        htmlBody = "<body> ";
        for (int i = 1; i <= 10; i++) {
            if (headings[i] != null) {
                htmlBody += "<h2> " + headings[i] + " </h2>";
            }
            if (paragraphs[i] != null) {
                htmlBody += "<p> " + paragraphs[i] + " </p>";
            }
            i++;
        }
        htmlBody += "</body></div></html>";
        return htmlBody;
    }

}
