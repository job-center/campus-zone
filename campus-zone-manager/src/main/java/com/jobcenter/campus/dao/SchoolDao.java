package com.jobcenter.campus.dao;

import com.jobcenter.campus.entity.School;

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
}
