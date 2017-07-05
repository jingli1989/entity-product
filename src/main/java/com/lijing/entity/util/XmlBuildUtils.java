package com.lijing.entity.util;


import com.lijing.entity.config.FileConfig;
import com.lijing.entity.dal.dto.ColumnInfoDto;
import com.lijing.entity.dal.dto.TableInfoDto;
import com.lijing.entity.model.EntityInfo;
import com.lijing.entity.model.FileInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * xml生成工具
 * Created by Lijing on 2017/7/4.
 */
@Slf4j
public class XmlBuildUtils {

    public static String buildHead(){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >";
    }

    public static String buildNameSpace(String mapperPath,String mapperName){
        return "<mapper namespace=\""+mapperPath+"."+mapperName+"\" >";
    }

    /**
     * 构建xml文件
     * @param entityInfo 表信息
     * @param fileConfig 文件存储信息
     * @return xml文件内容
     */
    public static String buildResultMap(EntityInfo entityInfo, FileConfig fileConfig){
        TableInfoDto tableInfoDto = entityInfo.getTableInfoDto();
        String entityClassName = tableInfoDto.getClassName();
        String dtoPath = fileConfig.getEntityPath()+"."+entityClassName;
        StringBuilder resultMap = new StringBuilder("   <resultMap id=\"BaseResultMap\" type=\""+dtoPath+"\" >\n");
        StringBuilder baseColumn = new StringBuilder("<sql id=\"all_column\">\n");
        StringBuilder setColumn = new StringBuilder("   <trim suffix=\"\" suffixOverrides=\",\">\n");
        StringBuilder queryColumn = new StringBuilder("<trim suffix=\"\" suffixOverrides=\"AND\">\n");
        StringBuilder insertValue = new StringBuilder("<trim suffix=\"\" suffixOverrides=\",\">\n");
        StringBuilder insertColumn = new StringBuilder("<trim suffix=\"\" suffixOverrides=\",\">\n");
        List<ColumnInfoDto> columnList = entityInfo.getColumnInfoDtoList();
        for (ColumnInfoDto columnInfoDto:columnList){
            String columnName = columnInfoDto.getColumnName().toUpperCase();
            String propertyName = columnInfoDto.getPropertyName();
            String columnType = columnInfoDto.getDataType().toUpperCase();
            resultMap.append("      <result column=\""+columnName+"\" property=\""+propertyName+"\" jdbcType=\""+columnType+"\"   />\n");
            baseColumn.append(columnName).append(" , ");
            setColumn.append("      <if test=\""+propertyName+"!=null\">\n").append(", "+columnName+" = #{"+propertyName+",jdbcType="+columnType+"}\n").append("</if>\n");
            queryColumn.append("        <if test=\""+propertyName+"!=null\">\n").append("AND "+columnName+" = #{"+propertyName+",jdbcType="+columnType+"}\n").append("</if>\n");
            insertValue.append("#{"+propertyName+",jdbcType="+columnType+"},\n");
            insertColumn.append(columnName).append(",\n");
        }
        StringBuilder result = new StringBuilder();
        result.append(buildHead()).append("\n").append(buildNameSpace(fileConfig.getMapperPath(),entityClassName+"Mapper")).append("\n");
        result.append(resultMap).append("   </resultMap>\n").append("\n");
        result.append(baseColumn).append("\n").append("</sql>\n");
        result.append("<insert id=\"insert\" parameterType=\""+dtoPath+"\" >").append("\n");
        result.append("INSERT INTO ").append(tableInfoDto.getTableName().toUpperCase()).append("(\n");
        result.append(insertColumn).append("</trim>) VALUES (\n").append(insertValue).append("</trim>)\n</insert>\n\n");
        result.append("<select id=\"select\" resultMap=\"BaseResultMap\" parameterType=\""+dtoPath+"\">\n");
        result.append("SELECT <include refid=\"all_column\" />\n").append("FROM ").append(tableInfoDto.getTableName()).append("\n");
        result.append("WHERE \n").append(queryColumn).append("</trim>\n    </select>\n\n");
        result.append("<select id=\"selectByPrimaryKey\" resultMap=\"BaseResultMap\" parameterType=\"Map\">\n");
        result.append("SELECT <include refid=\"all_column\" />\n").append("FROM ").append(tableInfoDto.getTableName()).append("\n");
        result.append("WHERE "+tableInfoDto.getPriColumn().getColumnName());
        result.append(" = #{"+tableInfoDto.getPriColumn().getPropertyName()+",jdbcType="+tableInfoDto.getPriColumn().getDataType().toUpperCase()+"}\n");
        result.append("</select>\n\n");
        result.append("<update id=\"updateByPrimaryKey\" parameterType=\""+dtoPath+"\">\n");
        result.append("UPDATE "+tableInfoDto.getTableName()+"\n" +
                "        <set> ").append(setColumn).append("</trim>\n</set>\n").append("   WHERE\n        "+tableInfoDto.getPriColumn().getColumnName());
        result.append(" =  #{"+tableInfoDto.getPriColumn().getPropertyName()+",jdbcType="+tableInfoDto.getPriColumn().getDataType().toUpperCase()+"}\n");
        result.append("</update>\n").append("</mapper>\n");
        return result.toString();
    }
}
