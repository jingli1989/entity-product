package com.lijing.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据库字段列索引枚举
 * Created by Lijing on 2017/7/4.
 */
@Getter
@AllArgsConstructor
public enum JdbcKeyType {
    PRI("PRI","主键索引"),
    UNI("UNI","唯一索引"),
    MUL("MUL","可重复索引"),
    ;
    private String code;
    private String desc;

}
