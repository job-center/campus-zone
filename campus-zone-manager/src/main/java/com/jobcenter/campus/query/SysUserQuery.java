package com.jobcenter.campus.query;

/**
 * Created by xiayun on 26/3/17.
 */
public class SysUserQuery extends BaseQuery {

    private String name;

    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
