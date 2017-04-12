package com.jobcenter.campus.service.authority.user.impl;

import com.google.common.collect.Lists;
import com.jobcenter.campus.common.utils.MD5Util;
import com.jobcenter.campus.dao.authority.school.SchoolDao;
import com.jobcenter.campus.dao.authority.user.SysUserDao;
import com.jobcenter.campus.dao.authority.user.SysUserRoleDao;
import com.jobcenter.campus.domin.SysUserInfoDo;
import com.jobcenter.campus.domin.page.Seed;
import com.jobcenter.campus.entity.authority.role.SysRole;
import com.jobcenter.campus.entity.authority.school.School;
import com.jobcenter.campus.entity.authority.user.SysUser;
import com.jobcenter.campus.model.Page;
import com.jobcenter.campus.model.SexEnum;
import com.jobcenter.campus.query.SysUserQuery;
import com.jobcenter.campus.service.authority.user.SysUserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by xiayun on 28/3/17.
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    @Autowired
    private SchoolDao schoolDao;

    @Override
    public Page<SysUserInfoDo> listSysUserInfos(Seed seed) {
        Assert.notNull(seed,"查詢參數seed不能为null");
        SysUserQuery sysUserQuery = new SysUserQuery();
        sysUserQuery.setPageSize(seed.getPageSize());
        sysUserQuery.setPageNum(seed.getPageNumber());
        if (StringUtils.isNotBlank(seed.getString("name"))){
            sysUserQuery.setName(seed.getString("name"));
        }

        Page<SysUser> sysUserPage = sysUserDao.listSysUsers(sysUserQuery);
        Page<SysUserInfoDo> result = new Page<>(sysUserPage.getPageNum(),sysUserPage.getPageSize(),sysUserPage.getTotal());
        List<SysUserInfoDo> list = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(sysUserPage.getResult())){

            sysUserPage.getResult().forEach(sysUser -> {
                SysUserInfoDo sysUserInfoDo = assembleSysUserInfo(sysUser);
                list.add(sysUserInfoDo);
            });
        }
        result.setResult(list);
        return result;
    }

    @Override
    public SysUserInfoDo getSysUserInfo(Integer sysUserId){
        Assert.notNull(sysUserId,"查询参数不能为空");
        SysUser sysUser = sysUserDao.getSysUserById(sysUserId);
        SysUserInfoDo sysUserInfoDo = assembleSysUserInfo(sysUser);
        return sysUserInfoDo;
    }

    @Override
    public boolean createSysUser(SysUser sysUser) {
        sysUser.setPassword(MD5Util.MD5(sysUser.getPassword()));
        sysUser.setIsDeleted((byte) 0);
        if (sysUser.getSex() == null){
            sysUser.setSex((byte) SexEnum.UNKNOWN.getCode());
        }
        return sysUserDao.createSysUser(sysUser);
    }

    private SysUserInfoDo assembleSysUserInfo(SysUser sysUser){
        if (sysUser == null){
            return null;
        }
        SysUserInfoDo sysUserInfoDo = new SysUserInfoDo();
        sysUserInfoDo.setSysUser(sysUser);//设置用户基本信息

        List<SysRole> sysRoles = sysUserRoleDao.listSysRolesByUserId(sysUser.getId());
        sysUserInfoDo.setRoles(sysRoles);//设置用户角色信息

        if (sysUser.getSchoolId() != null){
            //设置用户学校信息
            School school = schoolDao.getSchool(sysUser.getSchoolId());
            sysUserInfoDo.setSchool(school);
        }
        return sysUserInfoDo;
    }
}
