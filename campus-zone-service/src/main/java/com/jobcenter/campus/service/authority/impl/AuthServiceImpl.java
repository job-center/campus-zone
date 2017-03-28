package com.jobcenter.campus.service.authority.impl;

import com.jobcenter.campus.common.utils.MD5Util;
import com.jobcenter.campus.dao.SysUserDao;
import com.jobcenter.campus.entity.SysUser;
import com.jobcenter.campus.model.Page;
import com.jobcenter.campus.query.SysUserQuery;
import com.jobcenter.campus.service.authority.AuthService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiayun on 27/3/17.
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public SysUser doLogin(String name, String password) {
        SysUserQuery sysUserQuery = new SysUserQuery();
        sysUserQuery.setName(name);
        sysUserQuery.setPassword(MD5Util.MD5(password));
        Page<SysUser> page = sysUserDao.listSysUsers(sysUserQuery);
        return CollectionUtils.isNotEmpty(page.getResult())?page.getResult().get(0):null;
    }
}
