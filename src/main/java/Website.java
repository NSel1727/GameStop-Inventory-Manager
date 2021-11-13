import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Website {

    public String getLink(String name, String console)
    {
        try{
            Document doc = Jsoup.connect("https://www.google.com.au/search?q=gamestop" + name + console).get();
            Elements links = doc.getElementsByClass("g");
            Element firstLink = links.first();
            return firstLink.getElementsByTag("a").first().attr("href");
        }catch(Exception ex){
            System.out.println("An error has occurred :(");
        }
        return null;
    }
}
