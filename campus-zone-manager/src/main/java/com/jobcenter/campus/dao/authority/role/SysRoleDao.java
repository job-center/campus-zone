package com.jobcenter.campus.dao.authority.role;

import com.jobcenter.campus.entity.authority.role.SysRole;
import com.jobcenter.campus.model.Page;
import com.jobcenter.campus.query.SysRoleQuery;

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
public interface SysRoleDao {

    Page<SysRole> listSysRoles(SysRoleQuery query);

    List<SysRole> listAllSysRoles();
}
