package com.sise.hrms.service;

import com.sise.hrms.po.Training;
import com.sise.hrms.po.TrainingRecord;
import com.sise.hrms.repository.TrainingRecordRepository;
import com.sise.hrms.repository.TrainingRepository;
import com.sise.hrms.vo.TimeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by holyfrans on 2017/3/9.
 */
@Service
public class TrainingService {
    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private TrainingRecordRepository trainingRecordRepository;

    public Page<Training> findAllTrainingByPage(Integer page, Integer pageSize){
        return trainingRepository.findAll(new PageRequest(page - 1, pageSize));
    }
    public Page<Training> findAllTrainingByBeginTimeBetween(Integer page, Integer pageSize,String value){
        TimeVo timeVo = new TimeVo();
        timeVo.setValue(value);
        Map<String, Date> map = timeVo.getTimeMap();
        Date min = map.get("minDate");
        Date max = map.get("maxDate");
        return trainingRepository.findAllByBeginTimeBetween(new PageRequest(page - 1, pageSize), min, max );
    }
    public Page<Training> findAllTrainingByEndTimeBetween(Integer page, Integer pageSize,String value){
        TimeVo timeVo = new TimeVo();
        timeVo.setValue(value);
        Map<String, Date> map = timeVo.getTimeMap();
        Date min = map.get("minDate");
        Date max = map.get("maxDate");
        return trainingRepository.findAllByEndTimeBetween(new PageRequest(page - 1, pageSize),min,max);
    }
    public Page<Training> findAllTrainingByEndTimeAfter(Integer page, Integer pageSize){
        Date date = new Date();
        return trainingRepository.findAllByBeginTimeBeforeAndEndTimeAfter(new PageRequest(page - 1, pageSize),date, date);
    }


    public Training findOneTraining(Integer id){
        return trainingRepository.findOne(id);
    }

    @Transactional
    public Training saveTraining(Training training){
        return trainingRepository.save(training);
    }

    @Transactional
    public void deleteTrainingById(Integer id){
        Training training = trainingRepository.findOne(id);
        List<TrainingRecord> list = trainingRecordRepository.findAllByTraining(training);
        for (TrainingRecord trainingRecord : list){
            trainingRecordRepository.delete(trainingRecord);
        }
        trainingRepository.delete(id);
    }

    public Page<Training> findAllTrainingByTypeLikeAndPage(Integer page, Integer pageSize, String type) {
        return trainingRepository.findAllByTypeLike(new PageRequest(page - 1, pageSize), "%" + type + "%");
    }
}
