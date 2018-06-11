import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Youtube {
    Document document;

    public void load(String url) {
        try {
//            document = Jsoup.parse(new File(url), null);
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getInfo() {
        String s = document.html();
        int summ = 0;
        List<Integer> views = new ArrayList<>();
        System.out.println("подписчики");
        Matcher matcher = Pattern.compile("(?<=\"subscriberCountText\":\\{\"simpleText\":\")\\w+").matcher(s);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
        System.out.println("просмотры среднее");
        matcher = Pattern.compile("(?<=\"viewCountText\":\\{\"simpleText\":\")\\w+").matcher(s);
        while (matcher.find()) {
            try {
                views.add(Integer.parseInt(matcher.group()));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        for (Integer v : views) {
            summ += v;
        }
        System.out.println(summ / views.size());
//        System.out.println(s);
    }

    public void getLikes() {
        String s = document.html();
        System.out.println(s);
//        Elements elements = document.getElementsByTag("a");
//        for (Element e : elements) {
//            for (Element e1 : e.getElementsByAttributeValue("id", "thumbnail")) {
//                System.out.println(e.attr("href"));
//            }
//        }

    }

}
