package com.sise.hrms.service;

import com.sise.hrms.constant.StatusConstant;
import com.sise.hrms.po.Training;
import com.sise.hrms.po.TrainingRecord;
import com.sise.hrms.po.UserInfo;
import com.sise.hrms.repository.TrainingRecordRepository;
import com.sise.hrms.repository.TrainingRepository;
import com.sise.hrms.repository.UserInfoRepository;
import com.sise.hrms.vo.TrainingAndUserInfoListVo;
import com.sise.hrms.vo.TrainingAndUserInfoPkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by holyfrans on 2017/3/9.
 */
@Service
public class TrainingRecordService {
    @Autowired
    private TrainingRecordRepository trainingRecordRepository;
    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;

    public TrainingRecord findOneTrainingRecord(Integer id) {
        return trainingRecordRepository.findOne(id);
    }

    public Page<TrainingRecord> findAllTrainingRecordByPage(Integer page, Integer pageSize){
        return trainingRecordRepository.findAll(new PageRequest(page - 1, pageSize));
    }

    public List<TrainingAndUserInfoListVo> findAllTrainingAndUserInfoListVo(){
        List<TrainingAndUserInfoListVo> voList = new ArrayList<>();
        List<Training> trainingList = trainingRepository.findAll();
        for (Training training : trainingList){
            TrainingAndUserInfoListVo vo = new TrainingAndUserInfoListVo();
            List<TrainingRecord> list = trainingRecordRepository.findAllByTraining(training);
            List<UserInfo> userinfoList = new ArrayList<>();
            for (TrainingRecord trainingRecord : list){
                userinfoList.add(trainingRecord.getUserInfo());
            }
            vo.setTraining(training);
            if (userinfoList.size() > 0)
                vo.setUserInfoList(userinfoList);
            voList.add(vo);
        }
        return voList;
    }

    @Transactional
    public TrainingRecord saveTrainingRecordByVo(TrainingAndUserInfoPkVo vo){
        Training training = new Training();
        training.setId(vo.getTrainingId());
        UserInfo userInfo = userInfoRepository.findOne(vo.getUserInfoId());
        userInfo.setStatus(StatusConstant.TRAIN);
        userInfoRepository.save(userInfo);
        TrainingRecord trainingRecord = new TrainingRecord();
        trainingRecord.setUserInfo(userInfo);
        trainingRecord.setTraining(training);
        return saveTrainingRecord(trainingRecord);
    }

    @Transactional
    public TrainingRecord saveTrainingRecord(TrainingRecord trainingRecord){
        return trainingRecordRepository.save(trainingRecord);
    }

    @Transactional
    public void deleteTrainingRecordByVo(TrainingAndUserInfoPkVo vo){
        Training training = new Training();
        training.setId(vo.getTrainingId());
        UserInfo userInfo = userInfoRepository.findOne(vo.getUserInfoId());
        if (trainingRecordRepository.findAllByUserInfo(userInfo) == null){
            userInfo.setStatus(StatusConstant.NORMAL);
        }
        userInfoRepository.save(userInfo);
        TrainingRecord trainingRecord = trainingRecordRepository.findOneByTrainingAndUserInfo(training, userInfo);
        deleteTrainingRecord(trainingRecord);
    }

    @Transactional
    public void deleteTrainingRecord(TrainingRecord trainingRecord){
        trainingRecordRepository.delete(trainingRecord);
    }
}
