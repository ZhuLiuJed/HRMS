package com.sise.hrms.service;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by holyfrans on 2017/3/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.DEFAULT)
public class TestTrainingRecordService {
    @Autowired
    private TrainingRecordService trainingRecordService;

    @Test
    public void testFindAllTrainingRecordByPage(){
        trainingRecordService.findAllTrainingRecordByPage(1, 1);
    }

}
