package com.lijing.entity.dal.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 表信息
 * Created by Lijing on 2017/7/3.
 */
@Getter
@Setter
@ToString
public class TableInfoDto implements Serializable{
    /** 数据库名称 */
    private String tableSchema;
    /** 表名 */
    private String tableName;
    /** 表注释 */
    private String tableComment;
}
