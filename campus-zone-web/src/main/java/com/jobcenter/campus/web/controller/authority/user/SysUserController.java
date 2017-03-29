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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public APIResponse<Page<SysUserInfoDo>> listSysUserInfo(HttpServletRequest request, HttpServletResponse response, Seed seed){
        APIResponse apiResponse = new APIResponse();
        apiResponse.setSuccess(true);
        apiResponse.setCode(APIResponse.SUCCESS);
        apiResponse.setData(sysUserService.listSysUserInfos(seed));
        return apiResponse;
    }
}
