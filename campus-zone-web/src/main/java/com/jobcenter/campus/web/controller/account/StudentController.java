package com.jobcenter.campus.web.controller.account;

import com.jobcenter.campus.common.common.ResultEnum;
import com.jobcenter.campus.domin.page.Seed;
import com.jobcenter.campus.entity.account.Student;
import com.jobcenter.campus.entity.authority.school.School;
import com.jobcenter.campus.model.Page;
import com.jobcenter.campus.service.account.StudentService;
import com.jobcenter.campus.service.authority.school.SchoolService;
import com.jobcenter.campus.web.domin.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by xiayun on 5/4/17.
 */
@Controller
public class StudentController {

    private  static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;
    @Autowired
    private SchoolService schoolService;

    @RequestMapping(value = "/v1/account/students")
    @ResponseBody
    public ModelAndView listStudents(HttpServletRequest request, HttpServletResponse response, Seed seed){
        ModelAndView mav = new ModelAndView("account/students");
        Page<Student> result = studentService.listStudents(seed);

        seed.setResult(result.getResult());
        seed.setTotalSize(result.getTotal());
        mav.addObject("seed",seed);
        return mav;
    }


    @RequestMapping(value = "/v1/student",method = RequestMethod.POST)
    @ResponseBody
    public APIResponse createStudent(HttpServletRequest request,HttpServletResponse response,Student student){
        Assert.notNull(student,"新增参数不能为空");

        boolean result = studentService.createStudent(student);
        APIResponse apiResponse = new APIResponse(ResultEnum.parseResultEnum(result));
        return apiResponse;
    }
}
