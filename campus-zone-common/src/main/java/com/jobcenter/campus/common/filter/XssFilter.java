package com.jobcenter.campus.common.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p>
 * <br>==========================
 * <br> company：job-center
 * <br> system：campus-zone
 * <br> User：lzy.clement
 * <br> Date：2017/1/25
 * <br>==========================
 */
public class XssFilter implements Filter {
    FilterConfig filterConfig = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new XssHttpServletRequestWrapper(
                (HttpServletRequest) request), response);
    }
}
