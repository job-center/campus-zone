package com.jobcenter.campus.domin.school;

import com.jobcenter.campus.common.common.BaseEntity;
import com.jobcenter.campus.entity.authority.grade.Grade;
import com.jobcenter.campus.entity.authority.school.School;

/**
 * Created by xiayun on 8/4/17.
 */
public class GradeInfo extends BaseEntity {
    private Grade grade;
    private School school;

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
