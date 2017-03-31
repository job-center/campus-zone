package com.jobcenter.campus.web.controller.authority;

import com.jobcenter.campus.common.common.CommonConstant;
import com.jobcenter.campus.entity.authority.user.SysUser;
import com.jobcenter.campus.service.authority.AuthService;
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
import javax.servlet.http.HttpSession;

/**
 * Created by xiayun on 27/3/17.
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private AuthService authService;

    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "index")
    public String index(){
        return "index";
    }

    /**
     * 用户请求登录页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        if (session.getAttribute(CommonConstant.SESSEION_USER_KEY) != null){//用户登录后就直接跳转到index页面
            return "index";
        }
        return "login";
    }

    /**
     * 用户登录
     * @param request
     * @param response
     * @param name
     * @param password
     * @return
     */
    @RequestMapping(value = "doLogin")
    @ResponseBody
    public APIResponse doLogin(HttpServletRequest request,HttpServletResponse response,String name,String password){
        APIResponse apiResponse = new APIResponse();
        SysUser sysUser = authService.doLogin(name,password);
        if (sysUser == null){
            apiResponse.setCode(APIResponse.FAILED).setMsg("用户名或密码错误").setSuccess(false);
        } else {
            apiResponse.setMsg("登陆成功").setCode(APIResponse.SUCCESS).setSuccess(true);
            HttpSession session = request.getSession();
            session.setAttribute(CommonConstant.SESSEION_USER_KEY,sysUser);
        }
        return apiResponse;
    }

    @RequestMapping(value="logout",method={RequestMethod.GET,RequestMethod.POST})
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "login";
    }
}
