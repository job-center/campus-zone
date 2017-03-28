package com.jobcenter.campus.mapper;

import com.jobcenter.campus.entity.Geography;
import com.jobcenter.campus.entity.GeographyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GeographyMapper {
    int countByExample(GeographyExample example);

    int deleteByExample(GeographyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Geography record);

    int insertSelective(Geography record);

    List<Geography> selectByExample(GeographyExample example);

    Geography selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Geography record, @Param("example") GeographyExample example);

    int updateByExample(@Param("record") Geography record, @Param("example") GeographyExample example);

    int updateByPrimaryKeySelective(Geography record);

    int updateByPrimaryKey(Geography record);
}