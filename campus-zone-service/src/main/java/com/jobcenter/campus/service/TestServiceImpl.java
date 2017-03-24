package com.jobcenter.campus.service;

import com.jobcenter.campus.dao.UserDao;
import com.jobcenter.campus.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * <br>==========================
 * <br> company：job-center
 * <br> system：campus-zone
 * <br> User：lzy.clement
 * <br> Date：18/03/2017
 * <br>==========================
 */
@Service("testService")
public class TestServiceImpl implements TestService {

    @Autowired
    private UserDao userDao;

    @Override
    public void test() {
        System.out.println("this is a test service");
        User user = new User();
        user.setUsername("test");
        user.setUserpassword("ttt");
        userDao.insertOriUser(user);
        userDao.insertUser(user);
    }
}
