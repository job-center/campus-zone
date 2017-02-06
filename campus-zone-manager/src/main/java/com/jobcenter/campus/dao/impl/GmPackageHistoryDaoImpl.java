package com.jobcenter.campus.dao.impl;

import com.jobcenter.campus.dao.GmPackageHistoryDao;
import com.jobcenter.campus.entity.GmPackageHistory;
import com.jobcenter.campus.mapper.GmPackageHistoryMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * <br>==========================
 * <br> company：
 * <br> system：campus-zone
 * <br> developer：lzy.clement
 * <br> date：2017/2/6
 * <br>==========================
 */
@Repository
public class GmPackageHistoryDaoImpl implements GmPackageHistoryDao {

    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private GmPackageHistoryMapper gmPackageHistoryMapper;

    public void insertMapper(GmPackageHistory gmPackageHistory) {
        sqlSession.insert("com.jobcenter.campus.mapper.GmPackageHistoryMapper.insert",gmPackageHistory);
    }

    public void insertOriMapper(GmPackageHistory gmPackageHistory) {
        gmPackageHistoryMapper.insert(gmPackageHistory);
    }
}
