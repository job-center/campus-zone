package com.jobcenter.campus.dao.authority.user.impl;

import com.google.common.collect.Lists;
import com.jobcenter.campus.dao.authority.user.SysUserRoleDao;
import com.jobcenter.campus.entity.authority.role.SysRole;
import com.jobcenter.campus.entity.authority.user.SysUserRole;
import com.jobcenter.campus.entity.authority.user.SysUserRoleExample;
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

    @Override
    public boolean createSysUserRole(SysUserRole sysUserRole) {
        SysUserRoleExample example = new SysUserRoleExample();
        SysUserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo((byte) 0);
        criteria.andUserIdEqualTo(sysUserRole.getUserId());
        criteria.andRoleIdEqualTo(sysUserRole.getRoleId());


        int count = sysUserRoleMapper.countByExample(example);
        if (count > 1){//先检查是否曾经已经插入过
            return true;
        }
        return sysUserRoleMapper.insert(sysUserRole)>0;
    }
}
