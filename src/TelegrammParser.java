import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelegrammParser {
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
        Elements elements = document.getElementsByAttribute("class");
        boolean isCommunity = true;
        for (Element element : elements) {
            if (element.attr("class").equals("tgme_page_extra")) {
                System.out.println(element.text());
            }
            if (element.attr("class").equals("tgme_page_title")) {
                isCommunity = element.text().contains("Community");
                System.out.println("Community - " + isCommunity);
            }
        }
        if (!isCommunity) {
            load("https://web.telegram.org/#/im?tgaddr=tg%3A%2F%2Fresolve%3Fdomain%3Dquantnetworkannouncements");
//            for (Element element : elements.tagName("a")) {
//                if (element.attr("class").equals("tgme_action_button tgme_action_web_button")) {
//                    System.out.println(element.attr("href").toString());
//                    load("https://web.telegram.org/#/im?tgaddr=tg%3A%2F%2Fresolve%3Fdomain%3Dquantnetworkannouncements");
                    getChannelInfo();
//                }
//            }
        }
    }

    public void getChannelInfo() {
        final int THOUSAND = 1000;
        long summaryViews = 0;
        List<Integer> views = new ArrayList<>();
        Elements elements = document.getElementsByTag("span");
        for (Element element : elements) {
            if (element.attr("class").equals("im_message_views_cnt")) {
                System.out.println(element.text());

                try {
                    String value = element.text();
                    Pattern pattern = Pattern.compile("^\\d+[\\.,]*\\d*(?=[K])");
                    Matcher matcher = pattern.matcher(value);
                    if (matcher.find()) {
                        Double d = Double.valueOf(matcher.group());
                        d *= THOUSAND;
                        views.add(d.intValue());
                    } else if (!value.equals("")) {
                        views.add(Integer.parseInt(value));
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

            }
        }
        for (Integer n : views) {
            summaryViews += n;
        }
        System.out.println("среднее по просмотрам - " + summaryViews / views.size());
    }
}
