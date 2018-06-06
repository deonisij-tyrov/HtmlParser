public class Main {
    public static void main(String[] args) {
//        TwitterParser twitterParser = new TwitterParser();
//        twitterParser.load("https://twitter.com/Deco_Network");
//        twitterParser.getHeadInfo();
//        twitterParser.getPostsInfo();

        TelegrammParser telegrammParser = new TelegrammParser();
        telegrammParser.load("https://t.me/quantnetworkannouncements"); //c:\ide\Telegram_ Contact @quantnetworkannouncements.html
        telegrammParser.getInfo();
    }
}
