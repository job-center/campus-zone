package com.jobcenter.campus.dao;


import com.jobcenter.campus.entity.GmPackageHistory;

import javax.annotation.Resource;

@Resource
public interface GmPackageHistoryMapper {
    int insert(GmPackageHistory record);

    int insertSelective(GmPackageHistory record);
}