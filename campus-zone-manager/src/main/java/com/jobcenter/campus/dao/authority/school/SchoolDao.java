package com.jobcenter.campus.dao.authority.school;

import com.jobcenter.campus.entity.authority.grade.Grade;
import com.jobcenter.campus.entity.authority.group.Groups;
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

    boolean createSchool(School school);

    boolean updateSchool(List<School> schoolList);

    Page<School> listSchools(SchoolQuery query, boolean countAll);

    List<School> listAllSchools();

    List<Grade> listAllGradesBySchoolId(Integer schoolId);

    List<Groups> listAllGroupsByGradeId(Integer gradeId);

    Groups getGroup(Integer groupId);

    Grade getGrade(Integer gradeId);
}
