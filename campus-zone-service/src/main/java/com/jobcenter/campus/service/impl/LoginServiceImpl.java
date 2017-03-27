package com.jobcenter.campus.service.impl;

import com.jobcenter.campus.common.utils.MD5Util;
import com.jobcenter.campus.dao.SysUserDao;
import com.jobcenter.campus.entity.SysUser;
import com.jobcenter.campus.query.SysUserQuery;
import com.jobcenter.campus.service.AuthService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiayun on 27/3/17.
 */
@Service
public class LoginServiceImpl implements AuthService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public SysUser doLogin(String name, String password) {
        SysUserQuery sysUserQuery = new SysUserQuery();
        sysUserQuery.setName(name);
        sysUserQuery.setPassword(MD5Util.MD5(password));
        List<SysUser> list = sysUserDao.listSysUsers(sysUserQuery);
        return CollectionUtils.isNotEmpty(list)?list.get(0):null;
    }
}
