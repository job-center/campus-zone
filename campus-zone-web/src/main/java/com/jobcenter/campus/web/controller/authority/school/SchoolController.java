package com.jobcenter.campus.web.controller.authority.school;

import com.jobcenter.campus.domin.page.Seed;
import com.jobcenter.campus.entity.authority.school.School;
import com.jobcenter.campus.model.Page;
import com.jobcenter.campus.service.authority.school.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * <p>
 * <br>==========================
 * <br> company：job-center
 * <br> system：campus-zone
 * <br> User：lzy.clement
 * <br> Date：31/03/2017
 * <br>==========================
 */
@Controller
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @RequestMapping(value = "/v1/schools",method = RequestMethod.GET)
    public ModelAndView listSchoolInfo(Seed seed, ModelAndView modelAndView){
        Page<School> page = schoolService.listSchoolInfos(seed);
        seed.setResult(page.getResult());
        seed.setActionPath("/v1/schools");
        seed.setTotalSize(page.getTotal());

        modelAndView.setViewName("/sys/schoolList");
        modelAndView.addObject("seed",seed);
        return modelAndView;
    }
}
