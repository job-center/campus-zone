package com.jobcenter.campus.service.account.impl;

import com.jobcenter.campus.common.common.StatusEnum;
import com.jobcenter.campus.dao.account.StudentDao;
import com.jobcenter.campus.domin.page.Seed;
import com.jobcenter.campus.entity.account.Student;
import com.jobcenter.campus.model.Page;
import com.jobcenter.campus.query.StudentQuery;
import com.jobcenter.campus.service.account.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Created by xiayun on 5/4/17.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public Page<Student> listStudents(Seed seed) {
        Assert.notNull(seed,"查询参数seed不能为空");

        StudentQuery query = new StudentQuery();
        query.setPageNum(seed.getPageNumber());
        query.setPageSize(seed.getPageSize());
        if (StringUtils.isNotBlank(seed.getString("phoneNumber"))){
            query.setPhoneNumber(seed.getString("phoneNumber"));
        }
        if (StringUtils.isNotBlank(seed.getString("studentName"))){
            query.setStudentName(seed.getString("studentName"));
        }
        if (StringUtils.isNotBlank(seed.getString("studentNo"))){
            query.setStudentNo(seed.getString("studentNo"));
        }

        return studentDao.listStudents(query);
    }

    @Override
    public boolean createStudent(Student student) {
        student.setStatus(StatusEnum.STATUS_OPEN.getCode());
        return studentDao.createStudent(student);
    }
}
