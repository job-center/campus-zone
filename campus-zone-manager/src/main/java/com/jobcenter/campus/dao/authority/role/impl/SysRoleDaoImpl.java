package com.jobcenter.campus.dao.authority.role.impl;

import com.jobcenter.campus.dao.authority.role.SysRoleDao;
import com.jobcenter.campus.entity.*;
import com.jobcenter.campus.entity.authority.role.SysRole;
import com.jobcenter.campus.entity.authority.role.SysRoleExample;
import com.jobcenter.campus.intercepter.PageMybtisIntercepter;
import com.jobcenter.campus.mapper.SysRoleMapper;
import com.jobcenter.campus.model.Page;
import com.jobcenter.campus.query.SysRoleQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * <br>==========================
 * <br> company：job-center
 * <br> system：campus-zone
 * <br> User：lzy.clement
 * <br> Date：29/03/2017
 * <br>==========================
 */
@Repository
public class SysRoleDaoImpl implements SysRoleDao {

    @Autowired
    private SysRoleMapper sysRoleMapper;


    @Override
    public Page<SysRole> listSysRoles(SysRoleQuery query) {
        return listSysRoles(query, true);
    }

    public Page<SysRole> listSysRoles(SysRoleQuery query, boolean countAll) {
        SysRoleExample sysRoleExample = ExampleConvertor.convertSysRoleExample(query);
        PageMybtisIntercepter.startPage(query.getPageNum(),query.getPageSize());
        PageMybtisIntercepter.setLoadTotal(countAll);
        sysRoleMapper.selectByExample(sysRoleExample);
        Page<SysRole> result = PageMybtisIntercepter.endPage();
        return result;
    }
}
