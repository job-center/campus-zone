package com.jobcenter.campus.web.controller.authority.school;

import com.google.common.base.Splitter;
import com.jobcenter.campus.common.common.CommonConstant;
import com.jobcenter.campus.common.common.ResultEnum;
import com.jobcenter.campus.domin.page.Seed;
import com.jobcenter.campus.entity.authority.grade.Grade;
import com.jobcenter.campus.entity.authority.group.Groups;
import com.jobcenter.campus.entity.authority.role.SysRole;
import com.jobcenter.campus.entity.authority.school.School;
import com.jobcenter.campus.model.Page;
import com.jobcenter.campus.service.authority.school.SchoolService;
import com.jobcenter.campus.web.domin.APIResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


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
        seed.setActionPath("v1/schools");
        seed.setTotalSize(page.getTotal());

        modelAndView.setViewName("/sys/schoolList");
        modelAndView.addObject("seed",seed);
        return modelAndView;
    }

    @RequestMapping(value = "/v1/insertUpdateSchool",method = RequestMethod.POST)
    @ResponseBody
    public APIResponse insertUpdateSchool(School school){
        Assert.notNull(school,"添加学校不能为空");
        boolean result = false;
        if(school.getId() == null)
            result = schoolService.createSchool(school);
        else
            result = schoolService.updateSchoolByPrimaryKey(Arrays.asList(school));
        APIResponse apiResponse = new APIResponse(ResultEnum.parseResultEnum(result));
        return apiResponse;
    }

    @RequestMapping(value = "/v1/list/schools",method = RequestMethod.GET)
    @ResponseBody
    public APIResponse<List<School>> listAllSchools(){
        List<School> schoolList = schoolService.listAllSchools();
        APIResponse apiResponse = new APIResponse(ResultEnum.SUCCESS);
        apiResponse.setData(schoolList);
        return apiResponse;
    }

    @RequestMapping(value = "v1/schoolinfos/{id}", method = RequestMethod.GET)
    @ResponseBody
    public APIResponse sysRoleIndex(@PathVariable Integer id) {
        Assert.notNull(id,"查询学校id不能为空");
        School school = schoolService.getSchoolById(id);
        APIResponse apiResponse = new APIResponse(ResultEnum.SUCCESS);
        apiResponse.setData(school);
        return apiResponse;
    }

    @RequestMapping(value = "v1/schoolinfos/batchdelete/{schoolIds}",method = RequestMethod.DELETE)
    @ResponseBody
    public APIResponse removeSchool(@PathVariable(value = "schoolIds") String schoolIds) {
        if (StringUtils.isNotBlank(schoolIds)){
            List<String> schoolList = Splitter.on(",").splitToList(schoolIds);
            if (CollectionUtils.isNotEmpty(schoolList)){
                List<School> schools = schoolList.stream().map(m -> {
                    School school = new School();
                    school.setIsDeleted(CommonConstant.IS_DELETE_BYTE);
                    school.setId(NumberUtils.toInt(m,0));
                    return  school;
                }).collect(Collectors.toList());
                boolean flag = schoolService.updateSchoolByPrimaryKey(schools);
                if(flag)
                    return new APIResponse(ResultEnum.SUCCESS);
                else
                    return new APIResponse(ResultEnum.FAIL);
            }
            return new APIResponse(ResultEnum.SUCCESS);
        }else{
            return new APIResponse(ResultEnum.FAIL).setMsg("要删除的学校信息不能为空");
        }
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
