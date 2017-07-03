package com.lijing.entity.util;

/**
 * 列名和属性转换工具
 * Created by Administrator on 2017/7/3 0003.
 */
public class ColumnUtils {

    /**
     * 列名转换为属性名
     * @param columnName 列名
     * @return 属性名
     */
    public static String columnRecharnge(String columnName){
        columnName = columnName.toLowerCase();
        String[] cls = columnName.split("_");
        String propertyName = "";
        for (String str:cls){
            char[] cs=str.toCharArray();
            cs[0]-=32;
            propertyName +=String.valueOf(cs);
        }
        return propertyName;
    }

    /**
     * 属性名转换为列名
     * @param propertyName 属性名
     * @return 列名
     */
    public static String propertyRechange(String propertyName){
        char[] cs = propertyName.toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        for (char c:cs){
            if(c>=97&&c<=122){
                stringBuffer.append(c);
            }else {

                stringBuffer.append("_").append(c);
            }
        }
        return stringBuffer.toString().toUpperCase();
    }
}
