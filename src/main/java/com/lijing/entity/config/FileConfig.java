package com.lijing.entity.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 文件输出配置信息
 * Created by Lijing on 2017/7/3.
 */
@Getter
@Setter
@ToString
@Slf4j
@Component
public class FileConfig {
    /** 项目基本路径 */
    @Value("${basework}")
    private String basePath;
    /** 实体类路径(包路径) */
    @Value("${entity.package}")
    private String entityPath;
    /** mapper接口路径(包路径) */
    @Value("${mapper.package}")
    private String mapperPath;
    /** mapper xml文件路径 */
    @Value("${xml.path}")
    private String xmlPath;
    /** 是否使用lombook注解 true 使用 false 不使用 */
    @Value("${use.lombok}")
    private Boolean useLomBok;
}
