import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Alexa {
    Document document;
    public void load(String url) {
        try {
//            document = Jsoup.parse(new File(url), null);
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
