import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TwitterParser {
    Document document;

    public void load(String url) {
        try {
//            document = Jsoup.parse(new File(url), null);  //c:\ide\Deconet (@Deco_Network) _ Твиттер.html
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(document.toString());
    }

    public void getHeadInfo() {
        Elements elements = document.getElementsByTag("li");
        for (Element element : elements) {
            if (element.getElementsByAttribute("class").attr("data-nav").equals("followers")) {
                System.out.println("подписчики - " + element.getElementsByAttribute("class").attr("data-count"));
            }
            if (element.getElementsByAttribute("class").attr("data-nav").equals("favorites")) {
                System.out.println("лайки - " + element.getElementsByAttribute("class").attr("data-count"));
            }
        }
    }

    public void getPostsInfo() {
        int retwitsSummary = 0;
        int likesSummary = 0;

        Elements elements = document.getElementsByAttribute("class");
        List<Integer> retwits = new ArrayList<>();
        List<Integer> likes = new ArrayList<>();
        for (Element element : elements) {
            if (element.toString().contains("tweet")) {
//                if (element.getElementsByAttributeValue("class", "Icon Icon--small Icon--retweeted")) {
//                    System.out.println("пропустил твит " + element.getElementsByAttributeValue("class", "Icon Icon--small Icon--retweeted"));
//                    continue;
//                }
                Elements elements1 = element.getElementsByTag("button");
                for (Element element1 : elements1) {
                    if (element1.attr("class").equals("ProfileTweet-actionButton  js-actionButton js-actionRetweet")) {
                        System.out.println("retw" + getInfo(element1));
                        retwits.add(getInfo(element1));
                    }

                    if (element1.attr("class").equals("ProfileTweet-actionButton js-actionButton js-actionFavorite")) {
//                        System.out.println("like" + getInfo(element1));
                        likes.add(getInfo(element1));
                    }
                }
            }
        }


        for (Integer n : retwits) {
            retwitsSummary += n;
        }
        for (Integer n : likes) {
            likesSummary += n;
        }

        System.out.println("среднее по лайкам - " + likesSummary / likes.size());
        System.out.println("среднее по ретвитам - " + retwitsSummary / retwits.size());
    }

    private int getInfo(Element element) {
        final int HUNDRED = 100;
        int info = 0;
        for (Element element1 : element.getElementsByTag("span")) {
            if (element1.attr("class").equals("ProfileTweet-actionCountForPresentation")) {
                try {
                    String value = element1.getElementsByAttribute("class").text();
                    Pattern pattern = Pattern.compile("^\\d+,\\d*(?= +[а-яa-z])");                                              ///проверить
                    Matcher matcher = pattern.matcher(value);
                    if (matcher.find()) {
                        String[] bigNumber = matcher.group().split(",");
                        Integer n = Integer.parseInt(bigNumber[0] + bigNumber[1]) * HUNDRED;
                        info = (n);
                    } else if (!value.equals("")) {
                        info = (Integer.parseInt(value));
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        return info;
    }
}
