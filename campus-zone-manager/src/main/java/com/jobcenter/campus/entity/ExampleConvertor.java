package com.jobcenter.campus.entity;

import com.google.common.collect.Lists;
import com.jobcenter.campus.entity.authority.role.SysRoleExample;
import com.jobcenter.campus.entity.authority.school.SchoolExample;
import com.jobcenter.campus.entity.authority.user.SysUserExample;
import com.jobcenter.campus.entity.authority.user.SysUserRole;
import com.jobcenter.campus.entity.authority.user.SysUserRoleExample;
import com.jobcenter.campus.query.SchoolQuery;
import com.jobcenter.campus.query.SysRoleQuery;
import com.jobcenter.campus.query.SysUserQuery;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 普通查询对象转换为Mybatis example对象工具类
 * Created by xiayun on 26/3/17.
 */
public class ExampleConvertor {

    public static SysUserExample convertSysUserExample(SysUserQuery sysUserQuery){
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        if (sysUserQuery != null){
            if (sysUserQuery.getId() != null){
                criteria.andIdEqualTo(sysUserQuery.getId());
            }
            if (CollectionUtils.isNotEmpty(sysUserQuery.getIds())){
                criteria.andIdIn(Lists.newArrayList(sysUserQuery.getIds()));
            }
            if (StringUtils.isNotBlank(sysUserQuery.getName())){
                criteria.andNameEqualTo(sysUserQuery.getName());
            }
            if (StringUtils.isNotBlank(sysUserQuery.getPhoneNumber())){
                criteria.andPhonenumberEqualTo(sysUserQuery.getPhoneNumber());
            }
            if (StringUtils.isNotBlank(sysUserQuery.getPassword())){
                criteria.andPasswordEqualTo(sysUserQuery.getPassword());
            }
            if (StringUtils.isNotBlank(sysUserQuery.getOderByClause())){
                example.setOrderByClause(sysUserQuery.getOderByClause());
            }
        }
        return example;
    }

    public static SysRoleExample convertSysRoleExample(SysRoleQuery sysRoleQuery){
        SysRoleExample example = new SysRoleExample();
        SysRoleExample.Criteria criteria = example.createCriteria();
        if (sysRoleQuery != null){
            if (sysRoleQuery.getId() != null){
                criteria.andIdEqualTo(sysRoleQuery.getId());
            }
            if (CollectionUtils.isNotEmpty(sysRoleQuery.getIds())){
                criteria.andIdIn(Lists.newArrayList(sysRoleQuery.getIds()));
            }
            if (StringUtils.isNotBlank(sysRoleQuery.getRoleName())){
                criteria.andRoleNameEqualTo(sysRoleQuery.getRoleName());
            }
            if (StringUtils.isNotBlank(sysRoleQuery.getRoleDesc())){
                criteria.andRoleDescriptionEqualTo(sysRoleQuery.getRoleDesc());
            }
            if (sysRoleQuery.getIsDelete() != null){
                criteria.andIsDeletedEqualTo(sysRoleQuery.getIsDelete().byteValue());
            }
            if (StringUtils.isNotBlank(sysRoleQuery.getOderByClause())){
                example.setOrderByClause(sysRoleQuery.getOderByClause());
            }
        }
        return example;
    }

    public static SchoolExample convertSchoolExample(SchoolQuery schoolQuery){
        SchoolExample example = new SchoolExample();
        SchoolExample.Criteria criteria = example.createCriteria();
        if (schoolQuery != null){
            if (schoolQuery.getId() != null){
                criteria.andIdEqualTo(schoolQuery.getId());
            }
            if (CollectionUtils.isNotEmpty(schoolQuery.getIds())){
                criteria.andIdIn(Lists.newArrayList(schoolQuery.getIds()));
            }
            if (StringUtils.isNotBlank(schoolQuery.getAddress())){
                criteria.andAddressEqualTo(schoolQuery.getAddress());
            }
            if (StringUtils.isNotBlank(schoolQuery.getFullName())){
                criteria.andFullNameEqualTo(schoolQuery.getFullName());
            }
            if (StringUtils.isNotBlank(schoolQuery.getName())){
                criteria.andNameEqualTo(schoolQuery.getName());
            }
            if (StringUtils.isNotBlank(schoolQuery.getPersonCharge())){
                criteria.andPersonChargeEqualTo(schoolQuery.getPersonCharge());
            }
            if (StringUtils.isNotBlank(schoolQuery.getPostcode())){
                criteria.andPostcodeEqualTo(schoolQuery.getPostcode());
            }
            if (schoolQuery.getGeoraphyId() != null){
                criteria.andGeographyIdEqualTo(schoolQuery.getGeoraphyId());
            }
            if (schoolQuery.getType() != null){
                criteria.andTypeEqualTo(schoolQuery.getType());
            }
            if (StringUtils.isNotBlank(schoolQuery.getOderByClause())){
                example.setOrderByClause(schoolQuery.getOderByClause());
            }
        }
        return example;
    }

    public static SysUserRoleExample convertSysUserExample(SysUserRole sysUserRole){
        SysUserRoleExample example = new SysUserRoleExample();
        SysUserRoleExample.Criteria criteria = example.createCriteria();
        if (sysUserRole != null){
            if (sysUserRole.getId() != null){
                criteria.andIdEqualTo(sysUserRole.getId());
            }
            if (sysUserRole.getIsDeleted() != null){
                criteria.andIsDeletedEqualTo(sysUserRole.getIsDeleted());
            }
            if (sysUserRole.getRoleId() != null){
                criteria.andRoleIdEqualTo(sysUserRole.getRoleId());
            }
            if (sysUserRole.getUserId() != null){
                criteria.andUserIdEqualTo(sysUserRole.getUserId());
            }
        }
        return example;
    }
}
