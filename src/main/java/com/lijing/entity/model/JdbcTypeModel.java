package com.lijing.entity.model;

import com.lijing.entity.enums.JdbcTypeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * jdbc类型Model
 * Created by Administrator on 2017/7/2 0002.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class JdbcTypeModel implements Serializable{
    /**
     * jdbc类型
     */
    private String jdbc;
    /**
     * java类型
     */
    private String java;
    /**
     * java类型对应的路径
     */
    private String javaPackage;

    public JdbcTypeModel(JdbcTypeEnum jdbcTypeEnum){
        this.java = jdbcTypeEnum.getJava();
        this.javaPackage = jdbcTypeEnum.getJavaPackage();
        this.jdbc = jdbcTypeEnum.getJdbc();
    }
}
