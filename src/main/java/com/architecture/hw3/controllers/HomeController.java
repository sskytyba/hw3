package com.architecture.hw3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping
    public ModelAndView buy(@CookieValue(name = "_ga") String userId) {
        userId = userId != null ? userId.replace("GA1.1.", "") : UUID.randomUUID().toString();
        String message = "v=1&t=event&tid=UA-202602661-1&cid=" + userId + "&ea=Action&ec=Payment&el=Paid";
        restTemplate.postForObject("https://www.google-analytics.com/collect", message, String.class);
        return new ModelAndView("index");
    }


}
