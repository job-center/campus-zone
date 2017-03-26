package com.jobcenter.campus.service;


import com.jobcenter.campus.entity.SysUser;
import com.jobcenter.campus.model.Page;

/**
 * Created by clement on 18/03/2017.
 */
public interface TestService {

    void test();

    Page<SysUser> testSysUser();

}
