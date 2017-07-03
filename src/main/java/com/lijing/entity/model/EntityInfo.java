package com.lijing.entity.model;

import com.lijing.entity.dal.dto.ColumnInfoDto;
import com.lijing.entity.dal.dto.TableInfoDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 实体信息
 * Created by Lijing on 2017/7/3.
 */
@Getter
@Setter
@ToString
public class EntityInfo implements Serializable{
    /** 表信息 */
    private TableInfoDto tableInfoDto;
    /** 字段信息 */
    private List<ColumnInfoDto> columnInfoDtoList;
}
