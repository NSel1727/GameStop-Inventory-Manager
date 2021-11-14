import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class WebsiteTest {

    @Test
    void getPriceTest1() {
        try {
            String link = Website.getLink("Metroid Dread", "Nintendo Switch");
            Double result = Website.getPrice(new URL(link));
            assertTrue(result == 54.99);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getPriceTest2() {
        try {
            String link = Website.getLink("Banjo Kazooie", "Xbox 360");
            Double result = Website.getPrice(new URL(link));
            assertTrue(result == 14.99);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}