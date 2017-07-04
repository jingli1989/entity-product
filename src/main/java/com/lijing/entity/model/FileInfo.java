package com.lijing.entity.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 文件信息
 * Created by Lijing on 2017/7/4.
 */
@Getter
@Setter
@ToString
public class FileInfo {
    /** 文件名称 */
    private String fileName;
    /** 文件内容 */
    private String fileContent;
    /** 文件路径 */
    private String filePath;
}
