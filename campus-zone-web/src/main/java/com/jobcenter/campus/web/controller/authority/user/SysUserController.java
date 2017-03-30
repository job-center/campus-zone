package com.jobcenter.campus.web.controller.authority.user;

import com.jobcenter.campus.domin.SysUserInfoDo;
import com.jobcenter.campus.domin.page.Seed;
import com.jobcenter.campus.model.Page;
import com.jobcenter.campus.service.authority.SysUserService;
import com.jobcenter.campus.web.domin.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xiayun on 28/3/17.
 */
@Controller
public class SysUserController {

    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private SysUserService sysUserService;

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

        ModelAndView mav = new ModelAndView("/sys/sysUserList");
        mav.addObject("seed",seed);
        return mav;
    }

    @RequestMapping(value = "/v1/sysuser/{id}",method = RequestMethod.GET)
    public ModelAndView sysUserIndex(HttpServletRequest request, @PathVariable(value = "id") Integer id){
        SysUserInfoDo sysUserInfoDo = sysUserService.getSysUserInfo(id);

        ModelAndView mav = new ModelAndView("/sys/sysUserIndex");
        return mav;
    }
}
