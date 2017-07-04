package com.lijing.entity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务启动
 * Created by Lijing on 2017/7/4.
 */

@SpringBootApplication
@RestController
@ImportResource({"classpath:spring.xml"})
@RequestMapping(value = "/app")
public class AppService {

    @ResponseBody
    @RequestMapping(value = "/check")
    public String testConnection(){
        return "success";
    }

    public static void main(String[] args) {
        final ApplicationContext applicationContext = SpringApplication.run(AppService.class, args);
    }
}
