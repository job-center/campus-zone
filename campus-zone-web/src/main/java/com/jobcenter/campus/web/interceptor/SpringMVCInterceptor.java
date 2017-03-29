package com.jobcenter.campus.web.interceptor;

import com.jobcenter.campus.common.common.CommonConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by xiayun on 27/3/17.
 */
@Component("SpringMVCInterceptor")
public class SpringMVCInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(SpringMVCInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        // TODO 权限控制
        /*
        String actionName = request.getRequestURI();
        if (actionName.equalsIgnoreCase("/login") || actionName.equalsIgnoreCase("/doLogin")
                || actionName.startsWith("/resources"))
            return true;

        if (!actionName.startsWith("/resources")) {
            logger.info("------用户访问:" + actionName);
        }

        HttpSession session = request.getSession();

        if (session.getAttribute(CommonConstant.SESSEION_USER_KEY) == null) {
            response.sendRedirect("/login");
        }
        */
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }
}