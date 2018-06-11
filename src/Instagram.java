import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Instagram {
    Document document;

    public void load(String url) {
        try {
//            document = Jsoup.parse(new File(url), null);
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getLikes() {
        String s = document.html();
        Set<String> followers = new HashSet<>();
        Map<Integer, Integer> likes = new LinkedHashMap<>();
        Map<String, Integer> comments = new LinkedHashMap<>();
        Pattern pattern = Pattern.compile("\\bcontent=\".+ Followers\\b");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            followers.add(matcher.group());
        }

        matcher = Pattern.compile("(?<=\"taken_at_timestamp\":)\\d+(?=,\"edge_liked_by\":)").matcher(s);
        while (matcher.find()) {
            try {
                likes.put(Integer.parseInt(matcher.group()), null);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        for (Map.Entry<Integer, Integer> m : likes.entrySet()) {
            Integer key = m.getKey();
            matcher = Pattern.compile("(?<=\"taken_at_timestamp\":" + key + ",\"edge_liked_by\":\\{\"count\":)\\d+").matcher(s);
            while (matcher.find()) {
                try {
                    likes.put(key, Integer.parseInt(matcher.group()));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }



        for (Map.Entry<Integer, Integer> m : likes.entrySet()) {
            System.out.printf("\nkey - %s value - %s", m.getKey(), m.getValue());
        }


    }

    public void getComments() {
        String s = document.html();
    }


}
