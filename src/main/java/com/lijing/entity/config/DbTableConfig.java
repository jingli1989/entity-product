package com.lijing.entity.config;

import com.lijing.entity.exception.EntityServiceException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库表信息
 * Created by Lijing on 2017/7/3.
 */
@Getter
@Slf4j
@Component
public class DbTableConfig {
    /** 默认分隔符 */
    private static final String static_spilt = ",";
    /** 需要生成实体的数据库名称 */
    @Value("${product.dbName:#{null}}")
    private String dbName;
    /** 需要生成的表名称 为空时代表该库中所有表都需要生成实体 */
    @Value("${product.tableNames:#{null}}")
    private String tableNames;
    /** 多个表名和数据库名之间的分隔符 */
    @Value("${product.spilt:#{null}}")
    private String spiltStr;
    /** 数据库连接url */
    @Value("${jdbc.url:#{null}}")
    private String jdbcUrl;

    /**
     * 获取配置的数据库和表信息
     * @return 处理后的数据库和表信息
     */
    public Map<String,List<String>> getDbTableInfo(){
        if(StringUtils.isBlank(dbName)){
            dbName = getDBNameFromUrl();
        }
        Map<String,List<String>> map = new HashMap<String, List<String>>();
        if(!StringUtils.isBlank(tableNames)){
            String spilt = static_spilt;
            if(!StringUtils.isBlank(spiltStr)){
                spilt = spiltStr;
            }
            List<String> tableList = new ArrayList<String>();
            String[] tables = tableNames.split(spilt);
            for (String table:tables){
                tableList.add(table);
            }
            map.put(dbName,tableList);
        }else {
            map.put(dbName,new ArrayList<String>());
        }
        return map;
    }

    /**
     * 从数据库连接配置中获取数据库名称
     * @return 数据库名称
     */
    private String getDBNameFromUrl(){
        if(StringUtils.isBlank(jdbcUrl)){
            throw new EntityServiceException("DB config error","db-connection.properties jdbc.url is null");
        }
        String dbName = jdbcUrl.substring(jdbcUrl.lastIndexOf("/")+1);
        if(StringUtils.isBlank(dbName)){
            throw new EntityServiceException("DB config error","db-connection.properties jdbc.url is error ,can't get dbName");
        }
        return dbName;
    }
}
