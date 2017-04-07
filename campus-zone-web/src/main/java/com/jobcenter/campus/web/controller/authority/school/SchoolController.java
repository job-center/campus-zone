package com.jobcenter.campus.web.controller.authority.school;

import com.jobcenter.campus.common.common.ResultEnum;
import com.jobcenter.campus.domin.page.Seed;
import com.jobcenter.campus.entity.authority.grade.Grade;
import com.jobcenter.campus.entity.authority.group.Groups;
import com.jobcenter.campus.entity.authority.school.School;
import com.jobcenter.campus.model.Page;
import com.jobcenter.campus.service.authority.school.SchoolService;
import com.jobcenter.campus.web.domin.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


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

    @RequestMapping(value = "/v1/list/schools",method = RequestMethod.GET)
    @ResponseBody
    public APIResponse<List<School>> listAllSchools(){
        List<School> schoolList = schoolService.listAllSchools();
        APIResponse apiResponse = new APIResponse(ResultEnum.SUCCESS);
        apiResponse.setData(schoolList);
        return apiResponse;
    }

    @RequestMapping(value = "/v1/school/{schoolId}/grads")
    @ResponseBody
    public APIResponse<List<Grade>> listGradsOfSchool(@PathVariable(value = "schoolId",required = true)Integer schoolId){
        Assert.notNull(schoolId,"查询参数不能为空");
        List<Grade> gradeList = schoolService.listAllGradesBySchoolId(schoolId);
        APIResponse apiResponse = new APIResponse(ResultEnum.SUCCESS);
        apiResponse.setData(gradeList);
        return apiResponse;
    }

    @RequestMapping(value = "/v1/grade/{gradeId}/groups")
    @ResponseBody
    public APIResponse<List<Groups>> listGroupsOfGrade(@PathVariable(value = "gradeId",required = true)Integer gradeId){
        Assert.notNull(gradeId,"查询参数不能为空");

        List<Groups> groupsList = schoolService.listAllGroupsByGradeId(gradeId);
        APIResponse apiResponse = new APIResponse(ResultEnum.SUCCESS);
        apiResponse.setData(groupsList);

        return apiResponse;
    }
}
