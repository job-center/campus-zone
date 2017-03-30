package com.jobcenter.campus.domin;

import com.jobcenter.campus.entity.School;
import com.jobcenter.campus.entity.SysRole;
import com.jobcenter.campus.entity.SysUser;
import com.jobcenter.campus.model.SexEnum;

import java.util.List;

/**
 * Created by xiayun on 28/3/17.
 */
public class SysUserInfoDo {

    private SysUser sysUser;//系统用户信息
    private List<SysRole> roles;//系统用户对应的角色信息
    private School school;//系统用户对应的学校，不存在则为null

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getSex(){
        if (sysUser != null){
            SexEnum sexEnum = SexEnum.parseSexEnum(sysUser.getSex());
            if (sexEnum != null){
                return sexEnum.getDesc();
            }
        }
        return "";
    }

}
