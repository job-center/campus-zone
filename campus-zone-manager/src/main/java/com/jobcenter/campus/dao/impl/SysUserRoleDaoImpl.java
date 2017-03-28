package com.jobcenter.campus.dao.impl;

import com.google.common.collect.Lists;
import com.jobcenter.campus.dao.SysUserRoleDao;
import com.jobcenter.campus.entity.SysRole;
import com.jobcenter.campus.entity.SysUserRole;
import com.jobcenter.campus.entity.SysUserRoleExample;
import com.jobcenter.campus.mapper.SysRoleMapper;
import com.jobcenter.campus.mapper.SysUserMapper;
import com.jobcenter.campus.mapper.SysUserRoleMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * Created by xiayun on 28/3/17.
 */
@Repository
public class SysUserRoleDaoImpl implements SysUserRoleDao {

    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> listSysRolesByUserId(Integer sysUserId) {
        if (sysUserId == null ){
            return Collections.EMPTY_LIST;
        }
        SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
        sysUserRoleExample.createCriteria().andUserIdEqualTo(sysUserId);
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectByExample(sysUserRoleExample);
        List<SysRole> result = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(sysUserRoles)){
            sysUserRoles.forEach(sysUserRole -> {
                SysRole sysRole = sysRoleMapper.selectByPrimaryKey(sysUserRole.getRoleId());
                if (sysRole != null){
                    result.add(sysRole);
                }
            });
        }
        return result;
    }
}
