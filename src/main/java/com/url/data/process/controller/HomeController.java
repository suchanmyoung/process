package com.url.data.process.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home(Model model) {
        return "home";
    }

    @PostMapping
    public String home(Model model, @RequestParam("url") String url) throws Exception {
        Document document = Jsoup.connect(url).get();
        char[] select = document.html().toCharArray();
        for (char c : select) {
            int ascii = c;
            if (ascii > 47 && ascii < 58 || ascii>65 && ascii < 91 || ascii > 96 && ascii < 123) {
                //어떤 배열을 생성해서 이걸 저장해야할듯
                //그 후에 sort 메소드 돌려서..?
                System.out.print((char) ascii);
            }
        }
        
        model.addAttribute("document", select);
        return "home";
    }


}
