package com.test;

import com.lijing.entity.service.BuildFileService;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Lijing on 2017/7/4.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class Test {

    @Autowired
    private BuildFileService buildFileService;

    @org.junit.Test
    public void testFile(){
        buildFileService.buildEntity();
    }
}
