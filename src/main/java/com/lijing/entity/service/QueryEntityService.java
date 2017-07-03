package com.lijing.entity.service;

import com.lijing.entity.config.DbTableConfig;
import com.lijing.entity.dal.dto.ColumnInfoDto;
import com.lijing.entity.dal.dto.TableInfoDto;
import com.lijing.entity.dal.mapper.TableColumnInfoMapper;
import com.lijing.entity.model.EntityInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 实体信息查询服务
 * Created by Lijing on 2017/7/3.
 */
@Slf4j
@Service
public class QueryEntityService {
    /** 表信息数据库查询服务 */
    @Autowired
    private TableColumnInfoMapper tableColumnInfoMapper;
    /** 数据库配置 */
    @Autowired
    private DbTableConfig dbTableConfig;

    public void getEntityInfo(){

    }

    private List<EntityInfo> queryEntity(String dbName,List<String> tableNameList){
        if(tableNameList==null||tableNameList.isEmpty()){
            return queryAllByDB(dbName);
        }else {
            return queryTableInfo(dbName, tableNameList);
        }
    }

    /**
     * 根据数据库名称，查询该库中所有表信息
     * @param dbName 数据库名称
     * @return 表信息
     */
    private List<EntityInfo> queryAllByDB(String dbName){
        List<EntityInfo> result = new ArrayList<EntityInfo>();
        List<TableInfoDto> tableInfoDtoList = tableColumnInfoMapper.selectTable(dbName, null);
        if(tableInfoDtoList!=null&&!tableInfoDtoList.isEmpty()){
            for (TableInfoDto tableInfoDto:tableInfoDtoList){
                result.add(queryColumnInfo(dbName,tableInfoDto));
            }
        }
        return result;
    }

    /**
     * 根据数据库名称和表名查询表信息
     * @param dbName 数据库名称
     * @param tableNameList 表名称集合
     * @return 表信息
     */
    private List<EntityInfo> queryTableInfo(String dbName,List<String> tableNameList){
        List<EntityInfo> result = new ArrayList<EntityInfo>();
        for (String tableName : tableNameList){
            List<TableInfoDto> list = tableColumnInfoMapper.selectTable(dbName, tableName);
            if(list!=null&&list.size()==1){
                TableInfoDto tableInfoDto = list.get(0);
                result.add(queryColumnInfo(dbName,tableInfoDto));
            }
        }
    }

    /**
     * 根据数据库名称和表信息查询该表的列信息
     * @param dbName 数据库名称
     * @param tableInfoDto 表信息
     * @return 该表列信息
     */
    private EntityInfo queryColumnInfo(String dbName,TableInfoDto tableInfoDto){
        List<ColumnInfoDto> columnInfoDtoList = tableColumnInfoMapper.selectColumn(dbName, tableInfoDto.getTableName());
        if(columnInfoDtoList!=null) {
            EntityInfo entityInfo = new EntityInfo();
            entityInfo.setTableInfoDto(tableInfoDto);
            entityInfo.setColumnInfoDtoList(columnInfoDtoList);
            return entityInfo;
        }
        return null;
    }

}
