package com.jobcenter.campus.service.authority.role;

import com.jobcenter.campus.domin.SysUserInfoDo;
import com.jobcenter.campus.domin.page.Seed;
import com.jobcenter.campus.entity.SysRole;
import com.jobcenter.campus.model.Page;

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
}