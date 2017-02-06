package com.jobcenter.campus.common.interceptor;


import com.google.common.base.Strings;
import com.jobcenter.campus.common.common.CommonConstant;
import org.perf4j.StopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * <br>==========================
 * <br> company：job-center
 * <br> system：campus-zone
 * <br> User：lzy.clement
 * <br> Date：2017/1/25
 * <br>==========================
 */
public class CostTimeInteceptor extends HandlerInterceptorAdapter {

    private static final String STOPWATCH = CommonConstant.STOPWATCH;
    private static final Logger logger = LoggerFactory.getLogger(CostTimeInteceptor.class);
    private static final Logger prefLogger = LoggerFactory.getLogger("webTimingLogger");
    private final static int SLOW_TIME = 500;
    private final static int CONNECT_SLOW_TIME = 1000;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            StopWatch stopWatch = new Slf4JStopWatch(prefLogger);
            request.setAttribute(STOPWATCH, stopWatch);
        } catch (Exception e) {
            logger.error("CostTimeInteceptor error:" + request.getRequestURI(), e);
            return false;
        }
        return true;
    }

    private String getIp(HttpServletRequest request) {
        String sff = request.getHeader("X-Forwarded-For");// 根据nginx的配置，获取相应的ip
        if (Strings.isNullOrEmpty(sff)) {
            sff = request.getHeader("X-Real-IP");
        }
        if (Strings.isNullOrEmpty(sff)) {
            return Strings.isNullOrEmpty(request.getRemoteAddr()) ? "" : request.getRemoteAddr();
        }
        String[] ips = sff.split(",");
        String realip = ips[0];
        return realip;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        try {
            Object stopWatchObject = request.getAttribute(STOPWATCH);
            if (stopWatchObject != null) {
                StopWatch stopWatch = (StopWatch) stopWatchObject;

                StringBuilder tagBuilder = new StringBuilder(request.getRequestURI());
                String url = tagBuilder.toString();

                //是否为第三方请求
                if (url.contains("/connect/")) {
                    if (stopWatch.getElapsedTime() >= CONNECT_SLOW_TIME) {
                        tagBuilder.append(".slow");
                    }
                } else {
                    if (stopWatch.getElapsedTime() >= SLOW_TIME) {
                        tagBuilder.append(".slow");
                    }
                }
                stopWatch.stop(tagBuilder.toString());
            }
        } catch (Exception e) {
            logger.error("CostTimeInteceptor.afterCompletion error url=" + request.getRequestURL(), e);
        }
    }
}
