package com.jobcenter.campus.service.authority.school;

import com.jobcenter.campus.domin.page.Seed;
import com.jobcenter.campus.entity.authority.grade.Grade;
import com.jobcenter.campus.entity.authority.school.School;
import com.jobcenter.campus.model.Page;

import java.util.List;

/**
 * <p>
 * <br>==========================
 * <br> company：job-center
 * <br> system：campus-zone
 * <br> User：lzy.clement
 * <br> Date：31/03/2017
 * <br>==========================
 */
public interface SchoolService {

    Page<School> listSchoolInfos(Seed seed);

    List<School> listAllSchools();

    List<Grade> listAllGradesBySchoolId(Integer schoolId);
}
