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
    VARCHAR("VARCHAR","String"),
    INT("INT","Integer"),
    BIGINT("BIGINT","Long"),
    DATETIME("DATETIME","Date"),
    DOUBLE("DOUBLE","String"),
    FLOAT("FLOAT","String"),
    DECIMAL("DECIMAL","String"),
    TIMESTAMP("TIMESTAMP","String"),
    ;

    private String jdbc;
    private String java;
}
