public class Main {
    public static void main(String[] args) {
        TwitterParser twitterParser = new TwitterParser();
        twitterParser.load("https://twitter.com/Deco_Network");
        twitterParser.getHeadInfo();
        twitterParser.getPostsInfo();

//        TelegrammParser telegrammParser = new TelegrammParser();
//        telegrammParser.load("https://t.me/NeoPlace"); //c:\ide\Telegram_ Contact @quantnetworkannouncements.html
//        telegrammParser.getInfo();
        //https://web.telegram.org/#/im?tgaddr=tg%3A%2F%2Fresolve%3Fdomain%3Dquantnetworkannouncements
        //https://t.me/quantnetworkannouncements

//        FacebookParser facebookParser = new FacebookParser();
//        facebookParser.load("c:\\ide\\(77) Xsearch - Главная_F.html");
//        facebookParser.getInfo();
    }
}
