package com.jobcenter.campus.dao.authority.user;

import com.jobcenter.campus.entity.authority.role.SysRole;
import com.jobcenter.campus.entity.authority.user.SysUserRole;

import java.util.List;

/**
 * Created by xiayun on 28/3/17.
 */
public interface SysUserRoleDao {

    /**
     * 根据用户id获取用户对应的所有角色信息
     * @param sysUserId
     * @return
     */
    List<SysRole> listSysRolesByUserId(Integer sysUserId);

    /**
     * 创建用户角色关联信息
     * @param sysUserRole
     * @return
     */
    boolean createSysUserRole(SysUserRole sysUserRole);
}
