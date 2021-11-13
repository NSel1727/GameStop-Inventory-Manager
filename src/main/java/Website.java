import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import java.util.*;

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

    public double getPrice(URL url)
    {
        try {
            System.out.println(url.toString());
            URLConnection connection = url.openConnection();
            InputStream is = connection.getInputStream();
            Scanner scan = new Scanner(new InputStreamReader(is));
            String string = "";

            for(int i = 0; i < 5; i++)
            {
                while (!(string.contains("<span class=\"actual-price \">"))) {
                    string = scan.nextLine();
                }

                while(!string.contains("$"))
                {
                    string = scan.nextLine();

                }
            }
            return Double.parseDouble(string.trim().substring(1));
        }catch(java.io.IOException ex){
            System.out.println("\nAn error has occurred. :( \n");
        }
        return -1;
    }
}
