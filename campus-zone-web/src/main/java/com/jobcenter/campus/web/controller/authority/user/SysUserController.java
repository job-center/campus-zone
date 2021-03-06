package com.jobcenter.campus.web.controller.authority.user;

import com.jobcenter.campus.common.common.ResultEnum;
import com.jobcenter.campus.domin.SysUserInfoDo;
import com.jobcenter.campus.domin.page.Seed;
import com.jobcenter.campus.entity.authority.role.SysRole;
import com.jobcenter.campus.entity.authority.school.School;
import com.jobcenter.campus.entity.authority.user.SysUser;
import com.jobcenter.campus.model.Page;
import com.jobcenter.campus.service.authority.role.SysRoleService;
import com.jobcenter.campus.service.authority.school.SchoolService;
import com.jobcenter.campus.service.authority.user.SysUserService;
import com.jobcenter.campus.web.domin.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by xiayun on 28/3/17.
 */
@Controller
public class SysUserController {

    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 分页获取系统用户信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/v1/sysusers",method = RequestMethod.GET)
    public ModelAndView listSysUserInfo(HttpServletRequest request, HttpServletResponse response, Seed seed){
        Page<SysUserInfoDo> page = sysUserService.listSysUserInfos(seed);
        seed.setResult(page.getResult());
        seed.setActionPath("/v1/sysusers");
        seed.setTotalSize(page.getTotal());

        List<School> schools = schoolService.listAllSchools();
        ModelAndView mav = new ModelAndView("/sys/sysUserList");
        mav.addObject("schools",schools);
        mav.addObject("seed",seed);
        return mav;
    }

    @RequestMapping(value = "/v1/sysuser/{id}",method = RequestMethod.GET)
    public ModelAndView sysUserIndex(HttpServletRequest request, @PathVariable(value = "id") Integer id){
        SysUserInfoDo sysUserInfoDo = sysUserService.getSysUserInfo(id);
        List<SysRole> sysRoles = sysRoleService.listAllSysRole();

        ModelAndView mav = new ModelAndView("/sys/sysUserIndex");
        mav.addObject("sysuserinfo",sysUserInfoDo);
        mav.addObject("roles",sysRoles);
        return mav;
    }

    @RequestMapping(value = "/v1/sysuser",method = RequestMethod.POST)
    @ResponseBody
    public APIResponse createSysUser(HttpServletRequest request, HttpServletResponse response, SysUser sysUser){
        Assert.notNull(sysUser,"添加系统用户参数不能为空");
        boolean result = sysUserService.createSysUser(sysUser);
        APIResponse apiResponse = new APIResponse(ResultEnum.parseResultEnum(result));
        return apiResponse;
    }
}
