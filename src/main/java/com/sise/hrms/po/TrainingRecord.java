package com.sise.hrms.po;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by holyfrans on 2017/3/9.
 * 培训记录表
 */
@Entity(name = "trainingRecord")
public class TrainingRecord implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;
    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;

    @Column(length = 20)
    private String trainingStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrainingStatus() {
        return trainingStatus;
    }

    public void setTrainingStatus(String trainingStatus) {
        this.trainingStatus = trainingStatus;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }
}
