package com.jobcenter.campus.domin.account;

import com.jobcenter.campus.common.common.BaseEntity;
import com.jobcenter.campus.common.common.StatusEnum;
import com.jobcenter.campus.domin.school.GroupsInfo;
import com.jobcenter.campus.entity.account.Student;
import com.jobcenter.campus.model.SexEnum;

/**
 * Created by xiayun on 8/4/17.
 */
public class StudentInfo extends BaseEntity {

    private Student student;

    private GroupsInfo groupsInfo;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public GroupsInfo getGroupsInfo() {
        return groupsInfo;
    }

    public void setGroupsInfo(GroupsInfo groupsInfo) {
        this.groupsInfo = groupsInfo;
    }

    public String getStatus(){
        if (student == null || student.getStatus() == null){
            return "";
        }
        return StatusEnum.parseStatusEnum(student.getStatus()).getDesc();
    }

    public String getSex(){
        if (student == null || student.getSex() == null){
            return "";
        }
        return SexEnum.parseSexEnum(student.getSex()).getDesc();
    }

}
