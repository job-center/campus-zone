package com.jobcenter.campus.service.authority.role;

import com.jobcenter.campus.domin.page.Seed;
import com.jobcenter.campus.entity.authority.role.SysRole;
import com.jobcenter.campus.entity.authority.user.SysUser;
import com.jobcenter.campus.model.Page;

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
public interface SysRoleService {

    Page<SysRole> listSysRoleInfos(Seed seed);

    List<SysRole> listAllSysRole();

    boolean createSysRole(SysRole sysRole);

}
