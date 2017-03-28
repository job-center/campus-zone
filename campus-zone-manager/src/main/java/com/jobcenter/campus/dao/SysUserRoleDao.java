package com.jobcenter.campus.dao;

import com.jobcenter.campus.entity.SysRole;

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
}
