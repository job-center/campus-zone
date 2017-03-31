package com.jobcenter.campus.dao.authority.school.impl;

import com.jobcenter.campus.dao.authority.school.SchoolDao;
import com.jobcenter.campus.entity.*;
import com.jobcenter.campus.entity.authority.school.School;
import com.jobcenter.campus.entity.authority.school.SchoolExample;
import com.jobcenter.campus.intercepter.PageMybtisIntercepter;
import com.jobcenter.campus.mapper.SchoolMapper;
import com.jobcenter.campus.model.Page;
import com.jobcenter.campus.query.SchoolQuery;
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

    @Override
    public Page<School> listSchools(SchoolQuery query){
        return listSchools(query,true);
    }

    @Override
    public Page<School> listSchools(SchoolQuery query, boolean countAll) {
        SchoolExample schoolExample = ExampleConvertor.convertSchoolExample(query);
        PageMybtisIntercepter.startPage(query.getPageNum(),query.getPageSize());
        PageMybtisIntercepter.setLoadTotal(countAll);
        schoolMapper.selectByExampleWithBLOBs(schoolExample);
        Page<School> result = PageMybtisIntercepter.endPage();
        return result;
    }
}
