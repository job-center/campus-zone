package com.jobcenter.campus.mapper;


import com.jobcenter.campus.entity.GmPackageHistory;

public interface GmPackageHistoryMapper {

    int insert(GmPackageHistory record);

    int insertSelective(GmPackageHistory record);
}