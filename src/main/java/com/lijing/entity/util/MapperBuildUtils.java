package com.lijing.entity.util;

import com.lijing.entity.config.FileConfig;
import com.lijing.entity.dal.dto.ColumnInfoDto;
import com.lijing.entity.dal.dto.TableInfoDto;
import com.lijing.entity.model.EntityInfo;
import org.apache.commons.lang3.StringUtils;

/**
 * mapper文件生成
 * Created by Lijing on 2017/7/4.
 */
public class MapperBuildUtils {

    /**
     * 构建mapper文件
     * @param entityInfo
     * @param fileConfig
     * @return
     */
    public static String buildMapper(EntityInfo entityInfo, FileConfig fileConfig){
        TableInfoDto tableInfoDto = entityInfo.getTableInfoDto();
        String entityClassName = tableInfoDto.getClassName();
        String pClassName = firstToLower(entityClassName);
        ColumnInfoDto priColumn = tableInfoDto.getPriColumn();
        StringBuilder str = new StringBuilder("package ").append(fileConfig.getMapperPath()).append(";\r\n\n");
        str.append("import ").append(fileConfig.getEntityPath()).append(".").append(entityClassName).append(";\n\n");
        if(!StringUtils.isBlank(priColumn.getPropertyPackage())){
            str.append("import ").append(priColumn.getPropertyPackage()).append(";\n\n");
        }
        str.append("import java.util.List;\n\n");
        str.append("/**\n").append(" * ").append(tableInfoDto.getTableComment()).append("数据库操作\n").append(" * Created by Entity Product on 2017/7/4.\n").append(" */\n");
        str.append("public interface ").append(entityClassName).append("Mapper").append(" {\n\n");
        str.append("    /**\n").append("     * 新增").append(tableInfoDto.getTableComment()).append("\n");
        str.append("     * @param ").append(pClassName).append(" "+tableInfoDto.getTableComment()+"\n");
        str.append("     * @return 新增数据库操作结果\n").append("     */\n");
        str.append("    int insert("+entityClassName+" "+pClassName+");\n\n");
        str.append("    /**\n").append("     * 查询").append(tableInfoDto.getTableComment()).append("\n");
        str.append("     * @param ").append(pClassName).append(" 查询条件\n");
        str.append("     * @return 查询结果\n").append("     */\n");
        str.append("    List<"+entityClassName+"> select("+entityClassName+" "+pClassName+");\n\n");
        str.append("    /**\n").append("     * 根据主键"+priColumn.getColumnComment()+"查询").append(tableInfoDto.getTableComment()).append("\n");
        str.append("     * @param ").append(priColumn.getPropertyName()).append(" "+priColumn.getColumnComment()+"\n");
        str.append("     * @return 查询结果\n").append("     */\n");
        str.append("    "+entityClassName+" selectByPrimaryKey("+priColumn.getPropertyType()+" "+priColumn.getPropertyName()+");\n\n");
        str.append("    /**\n").append("     * 根据主键"+priColumn.getColumnComment()+"更新").append(tableInfoDto.getTableComment()).append("\n");
        str.append("     * @param ").append(pClassName).append(" 含主键的更新内容\n");
        str.append("     * @return 查询结果\n").append("     */\n");
        str.append("    int updateByPrimaryKey("+entityClassName+" "+pClassName+");\n\n");
        str.append("}\n");
        return str.toString();
    }

    public static String firstToLower(String property){
        char[] cs = property.toCharArray();
        if(cs[0]>=65&&cs[0]<=90){
            cs[0] += 32;
        }
        String propertyName="";
        propertyName += String.valueOf(cs);
        return propertyName;
    }

}
