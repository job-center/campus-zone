package com.jobcenter.campus.dao.account;

import com.jobcenter.campus.entity.account.Student;
import com.jobcenter.campus.model.Page;
import com.jobcenter.campus.query.StudentQuery;

/**
 * Created by xiayun on 5/4/17.
 */
public interface StudentDao {

    Page<Student> listStudents(StudentQuery query);

    boolean createStudent(Student student);

    Student getStudent(Integer studentId);

    boolean updateStudent(Student student);
}
