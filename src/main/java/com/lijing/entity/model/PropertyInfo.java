package com.lijing.entity.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 实体属性信息
 * Created by Lijing on 2017/7/4.
 */
@Getter
@Setter
@ToString
public class PropertyInfo {
    /** 列名 */
    private String columnName;
    /** 列注释 */
    private String columnComment;
    /** 数据类型 */
    private String dataType;
    /** 字段最大长度 */
    private Integer maxLength;
    /** 属性名称 */
    private String propertyName;
}
