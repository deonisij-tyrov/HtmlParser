import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class FacebookParser {
    Document document;

    public void load(String url) {
        try {
            document = Jsoup.parse(new File(url), null);
//            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getInfo() {
        Elements elements = document.getElementsByTag("div");
        for (Element element : elements) {
//            System.out.println(element.text());
            if (element.attr("class").equals("_4bl9")) {
                if (element.toString().contains("Подписан") || element.toString().contains("Нравится")) {
                    Elements elements1 = element.getAllElements();
                    for (Element element1 : elements1) {
                        if (element1.hasAttr("classs") == false) {
                            System.out.println(element1.text());
                        }
                    }
                }
            }
        }
    }
}
