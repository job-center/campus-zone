package com.jobcenter.campus.service.authority.school.impl;

import com.google.common.collect.Lists;
import com.jobcenter.campus.common.common.CommonConstant;
import com.jobcenter.campus.dao.authority.school.SchoolDao;
import com.jobcenter.campus.domin.page.Seed;
import com.jobcenter.campus.entity.authority.grade.Grade;
import com.jobcenter.campus.entity.authority.group.Groups;
import com.jobcenter.campus.entity.authority.school.School;
import com.jobcenter.campus.model.Page;
import com.jobcenter.campus.query.SchoolQuery;
import com.jobcenter.campus.service.authority.school.SchoolService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
@Service
public class SchoolServiceImpl implements SchoolService{

    @Autowired
    private SchoolDao schoolDao;

    @Override
    public Page<School> listSchoolInfos(Seed seed) {
        Assert.notNull(seed,"查詢參數seed不能为null");
        SchoolQuery schoolQuery = new SchoolQuery();
        schoolQuery.setPageSize(seed.getPageSize());
        schoolQuery.setPageNum(seed.getPageNumber());
        if (StringUtils.isNotBlank(seed.getString("name"))){
            schoolQuery.setName(seed.getString("name"));
        }
        schoolQuery.setIsDeleted(CommonConstant.IS_NOT_DELETE_INT);
        Page<School> schoolPage = schoolDao.listSchools(schoolQuery);
        Page<School> result = new Page<>(schoolPage.getPageNum(),schoolPage.getPageSize(),schoolPage.getTotal());
        List<School> list = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(schoolPage.getResult())){
            list = schoolPage.getResult();
        }
        result.setResult(list);
        return result;
    }

    @Override
    public List<School> listAllSchools() {
        return schoolDao.listAllSchools();
    }

    @Override
    public boolean createSchool(School school) {
        school.setIsDeleted(CommonConstant.IS_NOT_DELETE_BYTE);
        return schoolDao.createSchool(school);
    }

    @Override
    public boolean updateSchoolByPrimaryKey(List<School> schoolList) {
        if(CollectionUtils.isNotEmpty(schoolList)) {
            return schoolDao.updateSchool(schoolList);
        } else {
            return false;
        }
    }

    @Override
    public School getSchoolById(Integer id) {
        Assert.notNull(id,"查询参数不能为空");
        return schoolDao.getSchool(id);
    }

    @Override
    public List<Grade> listAllGradesBySchoolId(Integer schoolId) {
        return schoolDao.listAllGradesBySchoolId(schoolId);
    }

    @Override
    public List<Groups> listAllGroupsByGradeId(Integer gradeId) {
        return schoolDao.listAllGroupsByGradeId(gradeId);
    }
}
