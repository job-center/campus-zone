package com.jobcenter.campus.dao.authority.user;

import com.jobcenter.campus.entity.authority.user.SysUser;
import com.jobcenter.campus.model.Page;
import com.jobcenter.campus.query.SysUserQuery;

/**
 * Created by xiayun on 26/3/17.
 */
public interface SysUserDao {

    /**
     * 分页获取sysuser
     * @param query
     * @return
     */
    Page<SysUser> listSysUsers(SysUserQuery query);

    Page<SysUser> listSysUsers(SysUserQuery query,boolean countAll);

    SysUser getSysUserById(Integer sysUserId);

    boolean createSysUser(SysUser sysUser);
}
