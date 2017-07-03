package com.lijing.entity.dal.mapper;

import com.lijing.entity.dal.dto.ColumnInfoDto;
import com.lijing.entity.dal.dto.TableInfoDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 表和字段信息数据库操作
 * Created by Lijing on 2017/7/3.
 */
public interface TableColumnInfoMapper {

    /**TDirMobileHMapper.xml
     * 根据数据库名称、表名获取表信息
     * @param dbName 数据库名称
     * @param tableName 表名
     * @return 表信息
     */
    List<TableInfoDto> selectTable(@Param(value = "dbName")String dbName, @Param(value = "tableName")String tableName);

    /**
     * 根据数据库名称、表名称获取表字段信息
     * @param dbName 数据库名称
     * @param tableName 表名称
     * @return 字段信息
     */
    List<ColumnInfoDto> selectColumn(@Param(value = "dbName")String dbName, @Param(value = "tableName")String tableName);

}
