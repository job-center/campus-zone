package com.jobcenter.campus.dao.impl;

import com.jobcenter.campus.dao.SysUserDao;
import com.jobcenter.campus.entity.ExampleConvertor;
import com.jobcenter.campus.entity.SysUser;
import com.jobcenter.campus.entity.SysUserExample;
import com.jobcenter.campus.intercepter.PageMybtisIntercepter;
import com.jobcenter.campus.mapper.SysUserMapper;
import com.jobcenter.campus.model.Page;
import com.jobcenter.campus.query.SysUserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiayun on 26/3/17.
 */
@Repository
public class SysUserDaoImpl implements SysUserDao {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> listSysUsers(SysUserQuery query) {
        SysUserExample sysUserExample = ExampleConvertor.convertSysUserExample(query);
        List<SysUser> result = sysUserMapper.selectByExample(sysUserExample);
        return result;
    }

}
