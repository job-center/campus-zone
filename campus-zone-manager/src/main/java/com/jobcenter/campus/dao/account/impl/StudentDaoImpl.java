package com.jobcenter.campus.dao.account.impl;

import com.jobcenter.campus.dao.account.StudentDao;
import com.jobcenter.campus.entity.ExampleConvertor;
import com.jobcenter.campus.entity.account.Student;
import com.jobcenter.campus.entity.account.StudentExample;
import com.jobcenter.campus.intercepter.PageMybtisIntercepter;
import com.jobcenter.campus.mapper.StudentMapper;
import com.jobcenter.campus.model.Page;
import com.jobcenter.campus.query.StudentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by xiayun on 5/4/17.
 */
@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Page<Student> listStudents(StudentQuery query) {
        StudentExample example = ExampleConvertor.convertStudentExample(query);
        PageMybtisIntercepter.startPage(query.getPageNum(),query.getPageSize());
        studentMapper.selectByExample(example);
        Page<Student> result = PageMybtisIntercepter.endPage();
        return result;
    }
}
