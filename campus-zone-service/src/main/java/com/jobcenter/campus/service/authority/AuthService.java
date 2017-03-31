package com.jobcenter.campus.service.authority;

import com.jobcenter.campus.entity.authority.user.SysUser;

/**
 * 登录/权限相关业务
 * Created by xiayun on 27/3/17.
 */
public interface AuthService {

    SysUser doLogin(String name, String password);
}
