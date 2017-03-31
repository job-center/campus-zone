package com.jobcenter.campus.service.authority.user;

import com.jobcenter.campus.domin.SysUserInfoDo;
import com.jobcenter.campus.domin.page.Seed;
import com.jobcenter.campus.model.Page;

/**
 * Created by xiayun on 28/3/17.
 */
public interface SysUserService {

    Page<SysUserInfoDo> listSysUserInfos(Seed seed);

    SysUserInfoDo getSysUserInfo(Integer sysUserId);
}
