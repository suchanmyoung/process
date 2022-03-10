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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private static List<Character> filteredInt = new ArrayList<>();
    private static List<Character> filteredChar = new ArrayList<>();

    @GetMapping
    public String home(Model model) {
        return "home";
    }

    @PostMapping
    public String home(Model model, @RequestParam("url") String url) throws Exception {
        Document document = Jsoup.connect(url).get();
        char[] select = document.html().toCharArray();

        for (char c : select) {
            int asciiCode = c;
            if (asciiCode > 47 && asciiCode < 58) {
                filterToNumber(c);
            } else if (asciiCode > 64 && asciiCode < 91 || asciiCode > 96 && asciiCode < 123) {
                filterToChar(c);
            }
        }

        Collections.sort(filteredChar);
        Collections.sort(filteredInt);

        model.addAttribute("filteredChar", filteredChar);
        model.addAttribute("filteredInt", filteredInt);

        return "home";
    }

    public List<Character> filterToChar(char c) {
        filteredChar.add(c);
        return filteredChar;
    }

    public List<Character> filterToNumber(char c) {
        filteredInt.add(c);
        return filteredInt;
    }



}
