package com.lijing.entity.service;

import com.lijing.entity.config.FileConfig;
import com.lijing.entity.dal.dto.ColumnInfoDto;
import com.lijing.entity.enums.JdbcKeyType;
import com.lijing.entity.model.EntityInfo;
import com.lijing.entity.model.FileInfo;
import com.lijing.entity.model.JdbcTypeModel;
import com.lijing.entity.model.PropertyInfo;
import com.lijing.entity.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public FileInfo buildEntity(EntityInfo entityInfo,FileConfig fileConfig){
        FileInfo fileInfo = new FileInfo();
        StringBuilder entity = new StringBuilder();
        entity.append(EntityBuildUtils.buildPackage(fileConfig)).append("\r\n\n");
        if(fileConfig.getUseLomBok()) {
            entity.append(EntityBuildUtils.buildLombokImport()).append("\r\n\n");
        }
        if(fileConfig.getUseSerial()){
            entity.append("import java.io.Serializable;\n");
        }
        StringBuilder property = new StringBuilder();
        Map<String,String> importMap = new HashMap<String, String>();
        for (ColumnInfoDto columnInfoDto:entityInfo.getColumnInfoDtoList()){
            JdbcTypeModel jdbcTypeModel = JdbcTypeUtils.getJavaType(columnInfoDto.getDataType());
            importMap.put(jdbcTypeModel.getJava(),jdbcTypeModel.getJavaPackage());
            property.append("    ").append(EntityBuildUtils.propertyNote(columnInfoDto.getColumnComment())).append("\n");
            property.append("    ").append(EntityBuildUtils.buildProperty(columnInfoDto)).append("\n");
            columnInfoDto.setPropertyName(ColumnUtils.columnRecharge(columnInfoDto.getColumnName()));
            columnInfoDto.setPropertyType(jdbcTypeModel.getJava());
            columnInfoDto.setPropertyPackage(jdbcTypeModel.getJavaPackage());
            if(JdbcKeyType.PRI.getCode().equals(columnInfoDto.getColumnKey())){
                entityInfo.getTableInfoDto().setPriColumn(columnInfoDto);
            }
        }
        StringBuilder importJava = new StringBuilder();
        for (String importStr:importMap.values()){
            if(!StringUtils.isBlank(importStr)){
                importJava.append("import ").append(importStr).append(";\n");
            }
        }
        entity.append(importJava).append("\r\n\n").append(EntityBuildUtils.classNote(entityInfo.getTableInfoDto().getTableComment())).append("\r\n");
        if(fileConfig.getUseLomBok()){
            entity.append(EntityBuildUtils.buildAnnotation()).append("\n");
        }
        String className = ColumnUtils.tableRecharge(entityInfo.getTableInfoDto().getTableName());
        entityInfo.getTableInfoDto().setClassName(className);
        fileInfo.setFileName(className+".java");
        entity.append("public class ").append(className);
        if(fileConfig.getUseSerial()){
            entity.append(" implements Serializable");
        }
        entity.append(" {\r\n");
        entity.append(property).append("\r\n}\r\n");
        fileInfo.setFileContent(entity.toString());
        return fileInfo;
    }


    public void buildEntity(){
        List<EntityInfo> entityInfoList = queryEntityService.getEntity();
        for (EntityInfo entityInfo:entityInfoList){
            FileInfo fileInfo = buildEntity(entityInfo,fileConfig);
            String javaFilePath = fileConfig.getBasePath()+"/src/main/java/"+fileConfig.getEntityPath().replaceAll("\\.","/");
            log.info("生成结果:{}",fileInfo);
            FileUtils.createFile(javaFilePath,entityInfo.getTableInfoDto().getClassName()+".java",fileInfo.getFileContent());
            String mapperFilePath = fileConfig.getBasePath()+"/src/main/java/"+fileConfig.getMapperPath().replaceAll("\\.","/");
            String fileContent = MapperBuildUtils.buildMapper(entityInfo,fileConfig);
            FileUtils.createFile(mapperFilePath,entityInfo.getTableInfoDto().getClassName()+"Mapper.java",fileContent);

            String xmlFilePath = fileConfig.getBasePath()+"/src/main/"+fileConfig.getXmlPath();
            String xmlFileContent = XmlBuildUtils.buildResultMap(entityInfo,fileConfig);
            log.info("xml file path :{}",xmlFilePath);
            FileUtils.createFile(xmlFilePath,entityInfo.getTableInfoDto().getClassName()+"Mapper.xml",xmlFileContent);
        }
    }
}
