package com.jobcenter.campus.service.account;

import com.jobcenter.campus.domin.page.Seed;
import com.jobcenter.campus.entity.account.Student;
import com.jobcenter.campus.model.Page;

/**
 * Created by xiayun on 5/4/17.
 */
public interface StudentService {

    Page<Student> listStudents(Seed seed);

    boolean createStudent(Student student);
}
