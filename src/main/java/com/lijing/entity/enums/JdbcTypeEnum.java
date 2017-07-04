package com.lijing.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


/**
 * jdbc数据库类型枚举
 * Created by Administrator on 2017/7/2 0002.
 */
@Getter
@ToString
@AllArgsConstructor
public enum JdbcTypeEnum {

    VARCHAR("VARCHAR","String",""),
    INT("INT","Integer",""),
    BIGINT("BIGINT","Long",""),
    DATETIME("DATETIME","Date","java.util.Date"),
    DOUBLE("DOUBLE","BigDecimal","java.math.BigDecimal"),
    FLOAT("FLOAT","BigDecimal","java.math.BigDecimal"),
    DECIMAL("DECIMAL","BigDecimal","java.math.BigDecimal"),
    TIMESTAMP("TIMESTAMP","Date","java.util.Date"),
    ;
    /** jdbc类型*/
    private String jdbc;
    /** java类型 */
    private String java;
    /** java类型对应的路径 */
    private String javaPackage;
}
