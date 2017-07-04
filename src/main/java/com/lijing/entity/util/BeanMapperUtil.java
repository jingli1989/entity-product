package com.lijing.entity.util;

import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;

import java.util.Collection;
import java.util.List;

/**
 * 对象复制
 */
public class BeanMapperUtil {

    /**
     * 持有Dozer单例
     */
    private static DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    /**
     * 基于Dozer转换对象的类型
     * @param obj
     *          需要转换的对象
     * @param toObj
     *          转换后的对象
     * @param <T>
     *          返回对象类型泛型
     * @return
     *          返回对象
     */
    public static <T> T objConvert(Object obj, Class<T> toObj){
        if (null == obj)
            return null;
        return dozerBeanMapper.map(obj, toObj);
    }


    /**
     * 对象带值转换
     * @param sourceList           需要转换的集合
     * @param toObj                转换后对象类型
     * @param <T>                  返回对象类型泛型
     * @return                     返回对象
     */
    public static <T> List<T> mapList(Collection sourceList, Class<T> toObj){
        if (null == sourceList)
            return null;
        List<T> destinationList = Lists.newArrayList();
        for (Object sourceObject : sourceList){
            T destinationObject = dozerBeanMapper.map(sourceObject, toObj);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }

    public static void copy(Object source, Object toObj){
        if (null != source)
            dozerBeanMapper.map(source, toObj);
    }
}
