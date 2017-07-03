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
}
