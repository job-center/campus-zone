package com.jobcenter.campus.service.account.impl;

import com.google.common.collect.Lists;
import com.jobcenter.campus.common.common.StatusEnum;
import com.jobcenter.campus.dao.account.ParentDao;
import com.jobcenter.campus.dao.account.StudentDao;
import com.jobcenter.campus.dao.authority.school.SchoolDao;
import com.jobcenter.campus.domin.account.StudentInfo;
import com.jobcenter.campus.domin.page.Seed;
import com.jobcenter.campus.domin.school.GradeInfo;
import com.jobcenter.campus.domin.school.GroupsInfo;
import com.jobcenter.campus.entity.account.Parent;
import com.jobcenter.campus.entity.account.Student;
import com.jobcenter.campus.entity.authority.grade.Grade;
import com.jobcenter.campus.entity.authority.group.Groups;
import com.jobcenter.campus.entity.authority.school.School;
import com.jobcenter.campus.model.Page;
import com.jobcenter.campus.query.StudentQuery;
import com.jobcenter.campus.service.account.StudentService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by xiayun on 5/4/17.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private SchoolDao schoolDao;
    @Autowired
    private ParentDao parentDao;

    @Override
    public Page<StudentInfo> listStudents(Seed seed) {
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

        Page<Student> page = studentDao.listStudents(query);
        Page<StudentInfo> result = new Page<>(page.getPageNum(),page.getPageSize(),page.getTotal());
        if (CollectionUtils.isNotEmpty(page.getResult())){

            List<StudentInfo> studentInfos = Lists.newArrayList();
            page.getResult().forEach(x->{
                StudentInfo studentInfo = new StudentInfo();
                studentInfo.setStudent(x);
                GroupsInfo groupsInfo = new GroupsInfo();
                Groups groups = schoolDao.getGroup(x.getGroupId());
                groupsInfo.setGroups(groups);
                GradeInfo gradeInfo = new GradeInfo();
                Grade grade = schoolDao.getGrade(groups.getGradId());
                School school = schoolDao.getSchool(grade.getSchoolId());
                gradeInfo.setSchool(school);
                gradeInfo.setGrade(grade);
                groupsInfo.setGradeInfo(gradeInfo);
                studentInfo.setGroupsInfo(groupsInfo);

                if (x.getFatherId() != null) {
                    Parent father = parentDao.getParent(x.getFatherId());
                    studentInfo.setFather(father);
                }
                if (x.getMotherId() != null){
                    Parent mother = parentDao.getParent(x.getMotherId());
                    studentInfo.setMother(mother);
                }
                studentInfos.add(studentInfo);
            });
            result.setResult(studentInfos);
        }
        return result;
    }

    @Override
    public boolean createStudent(Student student) {
        student.setStatus(StatusEnum.STATUS_OPEN.getCode());
        return studentDao.createStudent(student);
    }

    @Override
    public boolean changeStudentStatus(Integer studentId) {
        Student student = studentDao.getStudent(studentId);
        if (student != null){
            if (student.getStatus() == 1){
                student.setStatus(StatusEnum.STATUS_CLOSE.getCode());
            }else if (student.getStatus() == 0){
                student.setStatus(StatusEnum.STATUS_OPEN.getCode());
            }
            return studentDao.updateStudent(student);
        }
        return false;
    }
}
