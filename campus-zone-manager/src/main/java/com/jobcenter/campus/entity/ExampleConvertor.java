package com.jobcenter.campus.entity;

import com.google.common.collect.Lists;
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
}
