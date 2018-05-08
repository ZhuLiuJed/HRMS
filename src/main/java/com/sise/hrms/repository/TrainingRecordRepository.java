package com.sise.hrms.repository;

import com.sise.hrms.po.Training;
import com.sise.hrms.po.TrainingRecord;
import com.sise.hrms.po.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by holyfrans on 2017/3/9.
 */
public interface TrainingRecordRepository extends JpaRepository<TrainingRecord, Integer> {
    public List<TrainingRecord> findAllByTraining(Training training);

    public TrainingRecord findOneByTrainingAndUserInfo(Training training, UserInfo userInfo);

    public List<TrainingRecord> findAllByUserInfo(UserInfo userInfo);
}
