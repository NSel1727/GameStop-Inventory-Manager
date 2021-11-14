import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Website {

    public static String getLink(String name, String console)
    {
        try{
            Document doc = Jsoup.connect("https://www.google.com.au/search?q=gamestop" + name + console).get();
            Elements links = doc.getElementsByClass("g");
            Element firstLink = links.first();
            return firstLink.getElementsByTag("a").first().attr("href");
        }catch(Exception ex){
            System.out.println("An error has occurred.");
        }
        return null;
    }

    public static double getPrice(URL url)
    {
        try {
            URLConnection connection = url.openConnection();
            InputStream is = connection.getInputStream();
            Scanner scan = new Scanner(new InputStreamReader(is));

            String curLine = "";

            String result = "";

            int i;

            loop:

            for(i = 0; i < 6; i++)
            {

                while (!(curLine.contains("<span class=\"actual-price \">"))) {
                    if(!scan.hasNextLine())
                    {
                        break loop;
                    }
                    curLine = scan.nextLine();
                }

                while(!curLine.contains("$"))
                {
                    if(!scan.hasNextLine())
                    {
                        break loop;
                    }
                    curLine = scan.nextLine();
                }

                result = curLine;
            }

            if(i < 5)
            {
                throw new Exception();
            }
            return Double.parseDouble(result.trim().substring(1));
        }catch(Exception ex){
            System.out.println("\nAn error has occurred. A price may not be available for this product");
        }
        return -1;
    }
}
