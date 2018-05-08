package com.sise.hrms.vo;

import com.sise.hrms.po.Training;
import com.sise.hrms.po.UserInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/3/18.
 */
public class TrainingAndUserInfoListVo {
    private Training training;
    private List<UserInfo> userInfoList;

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }
}
