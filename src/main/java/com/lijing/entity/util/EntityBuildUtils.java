package com.lijing.entity.util;

import com.lijing.entity.config.FileConfig;
import com.lijing.entity.dal.dto.ColumnInfoDto;

/**
 * 实体对象构建工具
 * Created by Administrator on 2017/7/3 0003.
 */
public class EntityBuildUtils {
    /**
     * 构建包路径
     * @param fileConfig 文件配置
     * @return 包路径
     */
    public static String buildPackage(FileConfig fileConfig){
        String packagePath = "package "+fileConfig.getEntityPath();
        return packagePath;
    }

    /**
     * 构建lombok注解引入
     * @return lombok注解引入
     */
    public static String buildLombokImport(){
        String lombokImport =  "import lombok.Getter;\r\n"
                +"import lombok.Setter;\r\n"
                +"import lombok.ToString;\r\n";
        return lombokImport;
    }

    /**
     * 构建lombok 注解
     * @return lombok注解
     */
    public static String buildAnnotation(){
        return "@Getter\n" +
                "@Setter\n" +
                "@ToString\n";
    }

    /**
     * 构建类注释
     * @param tableComment 表注释信息
     * @return 类注释信息
     */
    public static String classNote(String tableComment){
        return "/**\n" +
                " * "+tableComment+"\n" +
                " * Created by Entity Product on 2017/7/3.\n" +
                " */";
    }

    /**
     * 构建属性注释
     * @param columnComment 列注释
     * @return 属性注释
     */
    public static String propertyNote(String columnComment){
        return "/** "+columnComment+" */";
    }

    /**
     * 构建属性
     * @param columnInfoDto 列信息
     * @return 属性信息
     */
    public static String buildProperty(ColumnInfoDto columnInfoDto){
        String propertyStr = "      private "+ JdbcTypeUtils.getJavaType(columnInfoDto.getDataType())+" " +
                ""+ ColumnUtils.columnRecharnge(columnInfoDto.getColumnName())+";";
        return propertyStr;
    }
}
