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
            List<TableInfoDto> tableInfoDtoList = tableColumnInfoMapper.selectTable(dbName, null);
            if(tableInfoDtoList!=null&&!tableInfoDtoList.isEmpty()){
                for (TableInfoDto tableInfoDto:tableInfoDtoList){
                    List<ColumnInfoDto> columnInfoDtoList = tableColumnInfoMapper.selectColumn(dbName, tableInfoDto.getTableName());
                }

            }
        }else {
            List<TableInfoDto> tableInfoDtoList = new ArrayList<TableInfoDto>();
            for (String tableName : tableNameList){
                List<TableInfoDto> list = tableColumnInfoMapper.selectTable(dbName, tableName);
                if(list!=null&&list.size()==1){
                    tableInfoDtoList.add(list.get(0));
                }
            }
        }


    }

}
