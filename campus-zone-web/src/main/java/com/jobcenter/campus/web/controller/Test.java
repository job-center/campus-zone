package com.jobcenter.campus.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * <br>==========================
 * <br> company：job-center
 * <br> system：campus-zone
 * <br> User：lzy.clement
 * <br> Date：2017/1/25
 * <br>==========================
 */
@Controller
@RequestMapping("/test")
public class Test {

    private Logger logger = LoggerFactory.getLogger("Console");

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        logger.info("this is a test!!");
        System.out.printf("this is a test");
        return "test";
    }
}
