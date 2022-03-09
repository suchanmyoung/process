package com.url.data.process.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;


public class JsoupParserTest {
    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("https://github.com/suchanmyoung").get();
            Elements divTag = doc.getElementsByTag("div");
            System.out.println(divTag);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
