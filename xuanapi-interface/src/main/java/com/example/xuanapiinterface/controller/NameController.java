package com.example.xuanapiinterface.controller;

import com.example.xuanapiclientsdk.model.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 名称api
 * @author: xuan 
 * @date: 2023/1/19 15:43
 **/
@RestController
@RequestMapping("/name")
public class NameController {


    @GetMapping
    public String getNameByGet(String name) {
        return "Your name is " + name + ", get.";
    }

    @PostMapping
    public String getNameByPost(@RequestParam String name) {
        return "Your name is " + name + ", post.";
    }

    @PostMapping("/user")
    public String getUserNameByPost(@RequestBody User user, HttpServletRequest request) {
        String assessKey = request.getHeader("assessKey");
        String nonce = request.getHeader("nonce");
        String timeStamp = request.getHeader("timeStamp");
        String body = request.getHeader("body");
        String sign = request.getHeader("sign");
        // todo 校验
        // 数据库查ak sk

        // 时间戳与当前时间不能超过1分钟

        // nonce 随机数不能重复

        // body + sk 通过一样的签名算法生成 sign

        return "Your name is " + user.getUsername() + ", post.";
    }



}
