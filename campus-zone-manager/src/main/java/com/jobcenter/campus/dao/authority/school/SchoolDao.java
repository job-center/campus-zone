package com.jobcenter.campus.dao.authority.school;

import com.jobcenter.campus.entity.authority.grade.Grade;
import com.jobcenter.campus.entity.authority.school.School;
import com.jobcenter.campus.model.Page;
import com.jobcenter.campus.query.SchoolQuery;

import java.util.List;

/**
 * Created by xiayun on 28/3/17.
 */
public interface SchoolDao {

    /**
     * 通过学校id获取学校信息
     * @param schoolId
     * @return
     */
    School getSchool(Integer schoolId);

    Page<School> listSchools(SchoolQuery query);

    Page<School> listSchools(SchoolQuery query, boolean countAll);

    List<School> listAllSchools();

    List<Grade> listAllGradesBySchoolId(Integer schoolId);
}
