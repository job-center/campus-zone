package com.jobcenter.campus.dao;

import com.jobcenter.campus.entity.User;

/**
 * <p>
 * <br>==========================
 * <br> company：job-center
 * <br> system：campus-zone
 * <br> User：lzy.clement
 * <br> Date：24/03/2017
 * <br>==========================
 */
public interface UserDao {

    void insertUser(User user);

    void insertOriUser(User user);
}
