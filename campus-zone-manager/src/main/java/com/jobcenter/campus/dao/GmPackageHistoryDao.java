package com.jobcenter.campus.dao;

import com.jobcenter.campus.entity.GmPackageHistory;

/**
 * <p>
 * <br>==========================
 * <br> company：
 * <br> system：campus-zone
 * <br> developer：lzy.clement
 * <br> date：2017/2/6
 * <br>==========================
 */
public interface GmPackageHistoryDao {

    void insertMapper(GmPackageHistory gmPackageHistory);

    void insertOriMapper(GmPackageHistory gmPackageHistory);
}
