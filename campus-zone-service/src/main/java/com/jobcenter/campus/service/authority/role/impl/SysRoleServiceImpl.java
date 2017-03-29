package com.jobcenter.campus.service.authority.role.impl;

import com.google.common.collect.Lists;
import com.jobcenter.campus.dao.authority.role.SysRoleDao;
import com.jobcenter.campus.domin.page.Seed;
import com.jobcenter.campus.entity.SysRole;
import com.jobcenter.campus.model.Page;
import com.jobcenter.campus.query.SysRoleQuery;
import com.jobcenter.campus.service.authority.role.SysRoleService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * <p>
 * <br>==========================
 * <br> company：job-center
 * <br> system：campus-zone
 * <br> User：lzy.clement
 * <br> Date：29/03/2017
 * <br>==========================
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public Page<SysRole> listSysRoleInfos(Seed seed) {
        Assert.notNull(seed,"查詢參數seed不能为null");
        SysRoleQuery sysRoleQuery = new SysRoleQuery();
        sysRoleQuery.setPageSize(seed.getPageSize());
        sysRoleQuery.setPageNum(seed.getPageNumber());
        if (StringUtils.isNotBlank(seed.getString("roleName"))){
            sysRoleQuery.setRoleName(seed.getString("roleName"));
        }
        if (StringUtils.isNotBlank(seed.getString("roleDesc"))){
            sysRoleQuery.setRoleName(seed.getString("roleDesc"));
        }

        Page<SysRole> sysRolePage = sysRoleDao.listSysRoles(sysRoleQuery);
        Page<SysRole> result = new Page<>(sysRolePage.getPageNum(),sysRolePage.getPageSize(),sysRolePage.getTotal());
        List<SysRole> list = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(sysRolePage.getResult())){
            list = sysRolePage.getResult();
        }
        result.setResult(list);
        return result;
    }
}
