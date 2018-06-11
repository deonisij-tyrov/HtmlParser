import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IcoInfoOff {
    Document document;
    public void load(String url) {
        try {
//            document = Jsoup.parse(new File(url), null);
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printEmail() {
        System.out.println("почта");
        String string = document.getAllElements().html();
        Set<String> mails = new HashSet<>();
        Pattern pattern = Pattern.compile("[a-zA-Z]+\\w*@[a-zA-Z]*\\.[a-zA-Z]+");
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            mails.add(matcher.group());
        }
        for(String m : mails) {
            System.out.println(m);
        }
    }

    public void printUrls() {
        System.out.println("ссылки");
        Elements elements = document.getElementsByAttribute("href");
        Set<String> facebook = new HashSet<>();
        Set<String> telegramm = new HashSet<>();
        Set<String> linkedin = new HashSet<>();
        Set<String> twitter = new HashSet<>();
        Set<String> youtube = new HashSet<>();
        Set<String> instagram = new HashSet<>();
        Set<String> bitcointalk = new HashSet<>();
        for(Element e : elements) {
            if (e.attr("href").contains("facebook")) {
                facebook.add(e.attr("href"));
            }
            if(e.attr("href").contains("t.me")) {
                telegramm.add(e.attr("href"));
            }
            if(e.attr("href").contains("linkedin.com")) {
                linkedin.add(e.attr("href"));
            }
            if(e.attr("href").contains("twitter.com")) {
                twitter.add(e.attr("href"));
            }
            if(e.attr("href").contains("youtube.com")) {
                youtube.add(e.attr("href"));
            }
            if(e.attr("href").contains("instagram.com")) {
                instagram.add(e.attr("href"));
            }
            if(e.attr("href").contains("bitcointalk.org")) {
                bitcointalk.add(e.attr("href"));
            }
        }
        System.out.println("Facebook");
        print(facebook);
        System.out.println("telegramm");
        print(telegramm);
        System.out.println("linkedin");
        print(linkedin);
        System.out.println("twitter");
        print(twitter);
        System.out.println("instagram");
        print(instagram);
        System.out.println("bitcointalk");
        print(bitcointalk);
    }

    private void print(Set<String> set) {
        for(String s : set) {
            System.out.println(s);
        }
    }


}
