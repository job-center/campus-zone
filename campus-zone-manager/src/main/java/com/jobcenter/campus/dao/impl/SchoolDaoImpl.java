package com.jobcenter.campus.dao.impl;

import com.jobcenter.campus.dao.SchoolDao;
import com.jobcenter.campus.entity.School;
import com.jobcenter.campus.mapper.SchoolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by xiayun on 28/3/17.
 */
@Repository
public class SchoolDaoImpl implements SchoolDao {

    @Autowired
    private SchoolMapper schoolMapper;

    @Override
    public School getSchool(Integer schoolId) {
        if (schoolId == null){
            return null;
        }
        return schoolMapper.selectByPrimaryKey(schoolId);
    }
}
