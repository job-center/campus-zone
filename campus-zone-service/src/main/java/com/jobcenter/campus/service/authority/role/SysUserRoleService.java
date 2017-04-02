package com.jobcenter.campus.service.authority.role;

import com.jobcenter.campus.entity.authority.user.SysUserRole;

import java.util.List;

/**
 * Created by xiayun on 2/4/17.
 */
public interface SysUserRoleService {

    /**
     * 创建用户角色关联信息
     * @param sysUserRole
     * @return
     */
    boolean createSysUserRole(SysUserRole sysUserRole);

    boolean deleteBatchSysUserRoles(List<SysUserRole> sysUserRoles);
}
