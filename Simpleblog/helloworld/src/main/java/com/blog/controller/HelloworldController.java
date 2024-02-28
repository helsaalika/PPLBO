package com.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class HelloworldController {

    @RequestMapping("/")
    @ResponseBody
    public String getIndex(){
        return "<h1>Hello World!!!</h1>";
    }
}
