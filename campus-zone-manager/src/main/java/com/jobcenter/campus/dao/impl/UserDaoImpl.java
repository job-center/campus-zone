package com.jobcenter.campus.dao.impl;

import com.jobcenter.campus.dao.UserDao;
import com.jobcenter.campus.entity.User;
import com.jobcenter.campus.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * <br>==========================
 * <br> company：job-center
 * <br> system：campus-zone
 * <br> User：lzy.clement
 * <br> Date：24/03/2017
 * <br>==========================
 */
@Repository
public class UserDaoImpl implements UserDao {

//    @Autowired
//    private SqlSession sqlSession;

    @Autowired
    private UserMapper userMapper;
//
//    @Override
//    public void insertUser(User user) {
//        sqlSession.insert("com.jobcenter.campus.mapper.UserMapper.insert",user);
//    }

    @Override
    public void insertOriUser(User user) {
        userMapper.insert(user);
    }
}
