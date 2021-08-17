package com.zz;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZzDemo {
    static String  str = "<meta property=\"og:image\" content=\"https://images.pexels.com/photos/68147/waterfall-thac-dray-nur-buon-me-thuot-daklak-68147.jpeg?fit=crop&amp;h=1000&amp;mark=https%3A%2F%2Fassets.imgix.net%2F~text%3Fbg%3D80000000%26txt%3D%25E5%2585%258D%25E8%25B2%25BB%25E5%259C%2596%25E5%25BA%25AB%25E7%259B%25B8%25E7%2589%2587%26txtalign%3Dcenter%26txtclr%3Dfff%26txtfont%3DAvenir-Heavy%26txtpad%3D20%26txtsize%3D120%26w%3D1300&amp;markalign=center%2Cmiddle&amp;txt=pexels.com&amp;txtalign=center&amp;txtclr=eeffffff&amp;txtfont=Avenir-Heavy&amp;txtshad=10&amp;txtsize=60&amp;w=1500\" />\n" +
            "<link href=\"https://www.pexels.com/zh-tw/search/%E9%AB%98%E6%B8%85%E6%A1%8C%E9%9D%A2/\" rel=\"canonical\">\n" +
            "<link rel=\"alternate\" hreflang=\"en-US\" href=\"https://www.pexels.com/search/hd%20wallpapers/\" />\n" +
            "<link rel=\"alternate\" hreflang=\"pt-BR\" href=\"https://www.pexels.com/pt-br/procurar/pap%C3%A9is%20de%20parede%20em%20HD/\" />\n" +
            "<link rel=\"alternate\" hreflang=\"es-ES\" href=\"https://www.pexels.com/es-es/buscar/fondos%20de%20pantalla%20de%20alta%20definici%C3%B3n/\" />\n" +
            "<link rel=\"alternate\" hreflang=\"ca-ES\" href=\"https://www.pexels.com/ca-es/cerca/fons%20de%20pantalla%20HD/\" />\n" +
            "<link rel=\"alternate\" hreflang=\"de-DE\" href=\"https://www.pexels.com/de-de/suche/hd%20wallpapers/\" />\n" +
            "<link rel=\"alternate\" hreflang=\"it-IT\" href=\"https://www.pexels.com/it-it/cerca/sfondi%20in%20hd/\" />\n" +
            "<link rel=\"alternate\" hreflang=\"fr-FR\" href=\"https://www.pexels.com/fr-fr/chercher/fonds%20d&#39;%C3%A9cran%20hd/\" />\n" +
            "<link rel=\"alternate\" hreflang=\"sv-SE\" href=\"https://www.pexels.com/sv-se/sok/hd%20bakgrundsbilder/\" />\n" +
            "<link rel=\"alternate\" hreflang=\"id-ID\" href=\"https://www.pexels.com/id-id/pencarian/wallpaper%20hd/\" />\n" +
            "<link rel=\"alternate\" hreflang=\"pl-PL\" href=\"https://www.pexels.com/pl-pl/szukaj/tapety%20hd/\" />\n" +
            "<link rel=\"alternate\" hreflang=\"ja-JP\" href=\"https://www.pexels.com/ja-jp/search/HD%E3%81%AE%E5%A3%81%E7%B4%99/\" />\n" +
            "<link rel=\"alternate\" hreflang=\"zh-TW\" href=\"https://www.pexels.com/zh-tw/search/%E9%AB%98%E6%B8%85%E6%A1%8C%E9%9D%A2/\" />\n" +
            "<link rel=\"alternate\" hreflang=\"zh-CN\" href=\"https://www.pexels.com/zh-cn/search/%E9%AB%98%E6%B8%85%E6%A1%8C%E9%9D%A2/\" />\n" +
            "<link rel=\"alternate\" hreflang=\"ko-KR\" href=\"https://www.pexels.com/ko-kr/search/HD%20%EC%9B%94%ED%8E%98%EC%9D%B4%ED%8D%BC/\" />\n" +
            "<link rel=\"alternate\" hreflang=\"th-TH\" href=\"https://www.pexels.com/th-th/search/%E0%B8%A7%E0%B8%AD%E0%B8%A5%E0%B8%A5%E0%B9%8C%E0%B9%80%E0%B8%9B%E0%B9%80%E0%B8%9B%E0%B8%AD%E0%B8%A3%E0%B9%8C%20HD/\" />\n" +
            "<link rel=\"alternate\" hreflang=\"nl-NL\" href=\"https://www.pexels.com/nl-nl/zoeken/hd%20bureaubladen/\" />\n" +
            "<link rel=\"alternate\" hreflang=\"hu-HU\" href=\"https://www.pexels.com/hu-hu/kereses/nagy%20felbont%C3%A1s%C3%BA%20h%C3%A1tt%C3%A9rk%C3%A9pek/\" />\n" +
            "<link rel=\"alternate\" hreflang=\"vi-VN\" href=\"https://www.pexels.com/vi-vn/tim-kiem/h%C3%ACnh%20n%E1%BB%81n%20hd/\" />\n" +
            "<link rel=\"alternate\" hreflang=\"cs-CZ\" href=\"https://www.pexels.com/cs-cz/hledat/HD%20tapety/\" />\n" +
            "<link rel=\"alternate\" hreflang=\"da-DK\" href=\"https://www.pexels.com/da-dk/sog/HD-baggrunde/\" />\n" +
            "<link rel=\"alternate\" hreflang=\"fi-FI\" href=\"https://www.pexels.com/fi-fi/hae/hd%20taustakuva/\" />\n" +
            "<link rel=\"alternate\" hreflang=\"uk-UA\" href=\"https://www.pexels.com/uk-ua/search/HD-%D1%88%D0%BF%D0%B0%D0%BB%D0%B5%D1%80%D0%B8/\" />\n" +
            "<link rel=\"alternate\" hreflang=\"el-GR\" href=\"https://www.pexels.com/el-gr/search/%CF%84%CE%B1%CF%80%CE%B5%CF%84%CF%83%CE%B1%CF%81%CE%AF%CE%B5%CF%82%20HD/\" />\n" +
            "<link rel=\"alternate\" hreflang=\"ro-RO\" href=\"https://www.pexels.com/ro-ro/cauta/fundaluri%20HD/\" />\n" +
            "<link rel=\"alternate\" hreflang=\"nb-NO\" href=\"https://www.pexels.com/nb-no/sok/hd-bakgrunnsbilder/\" />\n" +
            "<link rel=\"alternate\" hreflang=\"sk-SK\" href=\"https://www.pexels.com/sk-sk/vyhladat/tapety%20vo%20vysokom%20rozl%C3%AD%C5%A1en%C3%AD/\" />\n" +
            "<link rel=\"alternate\" hreflang=\"tr-TR\" href=\"https://www.pexels.com/tr-tr/arama/hd%20duvar%20ka%C4%9F%C4%B1tlar%C4%B1/\" />\n" +
            "<link rel=\"alternate\" hreflang=\"ru-RU\" href=\"https://www.pexels.com/ru-ru/search/%D0%BE%D0%B1%D0%BE%D0%B8%20hd/\" />\n" +
            "<link href=\"https://www.pexels.com/zh-tw/rss/\" rel=\"alternate\" title=\"RSS\" type=\"application/rss+xml\" />\n" +
            "<script>\n" +
            "//<![CDATA[\n" +
            "//]]>\n" +
            "</script>";


    public static void main(String[] args) {
        Matcher matcher= Pattern.compile("https://www.*%9D%A2").matcher(str);
        List<String> listimgurl=new ArrayList<String>();
        while (matcher.find()){
            listimgurl.add(matcher.group());
        }
        System.out.println(listimgurl);
    }







}