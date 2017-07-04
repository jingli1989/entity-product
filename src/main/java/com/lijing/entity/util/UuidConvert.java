package com.lijing.entity.util;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * 日志自定义convert
 * Created by Lijing on 2016/11/11.
 */
public class UuidConvert extends ClassicConverter {

        @Override
        public String convert(ILoggingEvent event) {
            return LogUtil.getLogId();
        }
}
