package com.jobcenter.campus.service.authority.role.impl;

import com.jobcenter.campus.dao.authority.user.SysUserRoleDao;
import com.jobcenter.campus.entity.authority.user.SysUserRole;
import com.jobcenter.campus.service.authority.role.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiayun on 2/4/17.
 */
@Service
public class SysUserRoleImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Override
    public boolean createSysUserRole(SysUserRole sysUserRole) {
        sysUserRole.setIsDeleted((byte) 0);

        return sysUserRoleDao.createSysUserRole(sysUserRole);
    }
}
