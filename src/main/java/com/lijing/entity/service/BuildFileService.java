package com.lijing.entity.service;

import com.lijing.entity.config.FileConfig;
import com.lijing.entity.dal.dto.ColumnInfoDto;
import com.lijing.entity.model.EntityInfo;
import com.lijing.entity.model.JdbcTypeModel;
import com.lijing.entity.util.ColumnUtils;
import com.lijing.entity.util.EntityBuildUtils;
import com.lijing.entity.util.JdbcTypeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件生成服务
 * Created by Administrator on 2017/7/3 0003.
 */
@Slf4j
@Service
public class BuildFileService {

    /** 文件配置信息 */
    private final FileConfig fileConfig;
    /** 数据库表信息查询服务 */
    private final QueryEntityService queryEntityService;

    /**
     * 初始化构造方法
     * @param fileConfig 文件配置信息
     * @param queryEntityService 数据库表信息查询服务
     */
    @Autowired
    public BuildFileService(FileConfig fileConfig, QueryEntityService queryEntityService) {
        this.fileConfig = fileConfig;
        this.queryEntityService = queryEntityService;
    }

    /**
     * 构建实体对象String
     * @param entityInfo 实体对象信息
     * @param fileConfig 文件生成配置信息
     * @return 实体对象内容
     */
    public String buildEntity(EntityInfo entityInfo,FileConfig fileConfig){
        StringBuilder entity = new StringBuilder();
        entity.append(EntityBuildUtils.buildPackage(fileConfig)).append("\r\n");
        if(fileConfig.getUseLomBok()) {
            entity.append(EntityBuildUtils.buildLombokImport()).append("\r\n");
        }
        StringBuilder property = new StringBuilder();
        Map<String,String> importMap = new HashMap<String, String>();
        for (ColumnInfoDto columnInfoDto:entityInfo.getColumnInfoDtoList()){
            JdbcTypeModel jdbcTypeModel = JdbcTypeUtils.getJavaType(columnInfoDto.getDataType());
            importMap.put(jdbcTypeModel.getJava(),jdbcTypeModel.getJavaPackage());
            property.append("    ").append(EntityBuildUtils.propertyNote(columnInfoDto.getColumnComment())).append("\n");
            property.append("    ").append(EntityBuildUtils.buildProperty(columnInfoDto)).append("\n");
        }
        StringBuilder importJava = new StringBuilder();
        for (String importStr:importMap.values()){
            if(!StringUtils.isBlank(importStr)){
                importJava.append(importStr).append(";\n");
            }
        }
        entity.append(importJava).append("\r\n").append(EntityBuildUtils.classNote(entityInfo.getTableInfoDto().getTableComment())).append("\r\n");
        entity.append(EntityBuildUtils.buildAnnotation()).append("\n");
        entity.append("public class ").append(ColumnUtils.columnRecharnge(entityInfo.getTableInfoDto().getTableName())).append(" {\r\n");
        entity.append(property).append("\r\n}\r\n");
        return entity.toString();
    }


}
