package com.jobcenter.campus.dao.authority.school.impl;

import com.jobcenter.campus.dao.authority.school.SchoolDao;
import com.jobcenter.campus.entity.*;
import com.jobcenter.campus.entity.authority.grade.Grade;
import com.jobcenter.campus.entity.authority.grade.GradeExample;
import com.jobcenter.campus.entity.authority.group.Groups;
import com.jobcenter.campus.entity.authority.group.GroupsExample;
import com.jobcenter.campus.entity.authority.school.School;
import com.jobcenter.campus.entity.authority.school.SchoolExample;
import com.jobcenter.campus.intercepter.PageMybtisIntercepter;
import com.jobcenter.campus.mapper.GradeMapper;
import com.jobcenter.campus.mapper.GroupsMapper;
import com.jobcenter.campus.mapper.SchoolMapper;
import com.jobcenter.campus.model.Page;
import com.jobcenter.campus.query.SchoolQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiayun on 28/3/17.
 */
@Repository
public class SchoolDaoImpl implements SchoolDao {

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private GradeMapper gradeMapper;

    @Autowired
    private GroupsMapper groupsMapper;

    @Override
    public School getSchool(Integer schoolId) {
        if (schoolId == null){
            return null;
        }
        return schoolMapper.selectByPrimaryKey(schoolId);
    }

    @Override
    public Page<School> listSchools(SchoolQuery query){
        return listSchools(query,true);
    }

    @Override
    public Page<School> listSchools(SchoolQuery query, boolean countAll) {
        SchoolExample schoolExample = ExampleConvertor.convertSchoolExample(query);
        PageMybtisIntercepter.startPage(query.getPageNum(),query.getPageSize());
        PageMybtisIntercepter.setLoadTotal(countAll);
        schoolMapper.selectByExampleWithBLOBs(schoolExample);
        Page<School> result = PageMybtisIntercepter.endPage();
        return result;
    }

    @Override
    public List<School> listAllSchools() {
        SchoolExample example = new SchoolExample();
        example.setOrderByClause("name");
        return schoolMapper.selectByExample(example);
    }

    @Override
    public List<Grade> listAllGradesBySchoolId(Integer schoolId) {
        GradeExample example = new GradeExample();
        example.createCriteria().andSchoolIdEqualTo(schoolId);

        List<Grade> result = gradeMapper.selectByExample(example);
        return result;
    }

    @Override
    public List<Groups> listAllGroupsByGradeId(Integer gradeId) {
        GroupsExample example = new GroupsExample();
        example.createCriteria().andGradIdEqualTo(gradeId)
                .andIsDeletedEqualTo((byte) 0);

        List<Groups> result = groupsMapper.selectByExample(example);
        return result;
    }
}
