package com.jzwb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller - 首页
 */
@Controller("indexController")
@RequestMapping("/index")
public class IndexController {
    @RequestMapping
    public void index() {
        System.out.println("hello world!");
    }
}
