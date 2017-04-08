package com.jobcenter.campus.dao.account.impl;

import com.jobcenter.campus.dao.account.ParentDao;
import com.jobcenter.campus.entity.account.Parent;
import com.jobcenter.campus.mapper.ParentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by xiayun on 8/4/17.
 */
@Repository
public class ParentDaoImpl implements ParentDao {

    @Autowired
    private ParentMapper parentMapper;

    @Override
    public Parent getParent(Integer id) {
        return parentMapper.selectByPrimaryKey(id);
    }
}
