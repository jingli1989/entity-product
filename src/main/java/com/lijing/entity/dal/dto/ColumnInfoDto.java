package com.lijing.entity.dal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 字段信息
 * Created by Lijing on 2017/7/3.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ColumnInfoDto implements Serializable{
    /** 列名 */
    private String columnName;
    /** 列注释 */
    private String columnComment;
    /** 数据类型 */
    private String dataType;
    /** 字段最大长度 */
    private Integer maxLength;
    /** 列索引类型 PRI 主键 */
    private String columnKey;
    /** 是否允许为空 YES 允许为空 NO 不允许为空 */
    private String isNullAble;
    /** 属性名称 */
    private String propertyName;
    /** 属性类型 */
    private String propertyType;
    /** 属性包路径 */
    private String propertyPackage;
}
