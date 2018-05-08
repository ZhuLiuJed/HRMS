package com.sise.hrms.service;

import com.sise.hrms.po.Training;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by holyfrans on 2017/3/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.DEFAULT)
public class TestTrainingService {
    @Autowired
    private TrainingService trainingService;

    @Test
    public void testFindAllTrainingByPage(){
        Page result = trainingService.findAllTrainingByPage(1, 1);
        System.out.println(result.getContent());
    }
    @Test
    public void testFindOneTraining(){
        Training result = trainingService.findOneTraining(1);
        System.out.println(result);
    }
    @Test
    public void testSaveTraining(){
        Training training = new Training();
        training.setId(7);
        training.setType("正常培训");
        trainingService.saveTraining(training);
    }
    @Test
    public void testDeleteTrainingById(){
        trainingService.deleteTrainingById(8);
    }

}
