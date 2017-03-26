package com.jobcenter.campus.web.controller;

import com.jobcenter.campus.entity.SysUser;
import com.jobcenter.campus.model.Page;
import com.jobcenter.campus.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * <br>==========================
 * <br> company：job-center
 * <br> system：campus-zone
 * <br> User：lzy.clement
 * <br> Date：18/03/2017
 * <br>==========================
 */
@Controller
@RequestMapping("/testController")
public class TestController {

    @Autowired
    private TestService testService;

    private Logger logger = LoggerFactory.getLogger("Console");

    @RequestMapping("/test")
    public String test(){
        testService.test();
        return "test";
    }

    @RequestMapping("/sysuser")
    @ResponseBody
    public Page<SysUser> testSysUser(){
        return testService.testSysUser();
    }
}
