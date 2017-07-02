package com.lijing.entity.util;

import com.lijing.entity.enums.JdbcTypeEnum;
import com.lijing.entity.model.JdbcTypeModel;

import java.util.HashMap;
import java.util.Map;

/**
 * jdbc类型处理工具类
 * Created by Administrator on 2017/7/2 0002.
 */
public class JdbcTypeUtils {

    private static Map<String,JdbcTypeModel> typeModelMap;

    /**
     * 初始化jdbc类型数据
     */
    public static void init(){
        typeModelMap = new HashMap<String, JdbcTypeModel>();
        for (JdbcTypeEnum jdbcTypeEnum:JdbcTypeEnum.values()){
            typeModelMap.put(jdbcTypeEnum.getJdbc(),new JdbcTypeModel(jdbcTypeEnum));
        }
    }

    /**
     * 根据jdbc类型获取需要转换的java类型信息
     * @param jdbcType jdbc类型
     * @return java类型信息
     */
    public static JdbcTypeModel getJavaType(String jdbcType){
        if(typeModelMap==null){
            init();
        }
        return typeModelMap.get(jdbcType);
    }

}
