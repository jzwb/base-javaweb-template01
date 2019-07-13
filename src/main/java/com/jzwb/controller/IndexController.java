package com.jzwb.controller;

import com.jzwb.common.Message;
import com.jzwb.entity.Admin;
import com.jzwb.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Controller - 首页
 */
@Controller("indexController")
@RequestMapping("/index")
public class IndexController extends BaseController {

    @Resource(name = "testServiceImpl")
    private AdminService testService;

    @RequestMapping
    @ResponseBody
    public Message index() {
        List<Admin> list = testService.findAll();
        System.out.println(list.size());
        System.out.println("hello world!");
        return Message.success("请求成功");
    }
}