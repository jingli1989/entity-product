package com.lijing.entity.util;

import com.lijing.entity.config.FileConfig;
import com.lijing.entity.model.FileInfo;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 文件工具类
 * Created by Lijing on 2017/7/4.
 */
@Slf4j
public class FileUtils {

    public static void createFile(String filePath,String fileName,String dataContent){
        File fileDir = new File(filePath);
        if(fileDir.exists()){
            fileDir.mkdirs();
        }
        writeFile(filePath+"/"+fileName,dataContent);
    }

    public static void writeFile(String filePath,String fileContent){
        File file = new File(filePath);
        try {
            if(!file.exists()){
               file.createNewFile();
            }
            FileWriter fw =  new FileWriter(file);
            fw.write(fileContent);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String str = "com.lijing.test";
        System.out.println(str.replaceAll("\\.","!"));
    }
}
