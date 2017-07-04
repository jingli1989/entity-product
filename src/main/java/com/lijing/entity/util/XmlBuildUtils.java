package com.lijing.entity.util;


import com.lijing.entity.dal.dto.ColumnInfoDto;
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

    public static String buildResultMap(FileInfo fileInfo){
//        StringBuilder resultMap = new StringBuilder("<resultMap id=\"BaseResultMap\" type=\""+fileInfo.getEntityPackagePath()+"."+fileInfo.getClassName()+"\" >\n");
        StringBuilder baseColumn = new StringBuilder("<sql id=\"all_column\">\n");
        StringBuilder setColumn = new StringBuilder("");
        StringBuilder queryColumn = new StringBuilder("<trim suffix=\"\" suffixOverrides=\",\">");
        StringBuilder insertValue = new StringBuilder();
        StringBuilder insertColumn = new StringBuilder("<trim suffix=\"\" suffixOverrides=\",\">");

        return "";
    }
}
