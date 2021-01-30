package com.gorajski.restdocsdemo.controller;

import com.gorajski.restdocsdemo.model.MyJsonObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/string")
    public String getMain() {
        return "<h1>String of gems and pearls</h1>";
    }

    @GetMapping("/json")
    public MyJsonObject getJson() {
        return new MyJsonObject("of gold and silver");
    }
}
