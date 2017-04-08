package com.jobcenter.campus.domin.school;

import com.jobcenter.campus.common.common.BaseEntity;
import com.jobcenter.campus.entity.authority.grade.Grade;
import com.jobcenter.campus.entity.authority.group.Groups;

/**
 * Created by xiayun on 8/4/17.
 */
public class GroupsInfo extends BaseEntity {
    private Groups groups;
    private GradeInfo gradeInfo;

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public GradeInfo getGradeInfo() {
        return gradeInfo;
    }

    public void setGradeInfo(GradeInfo gradeInfo) {
        this.gradeInfo = gradeInfo;
    }
}
