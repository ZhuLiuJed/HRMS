package com.sise.hrms.service;

import com.sise.hrms.constant.SexConstant;
import com.sise.hrms.constant.StatusConstant;
import com.sise.hrms.po.UserInfo;
import com.sise.hrms.repository.UserInfoRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by holyfrans on 2017/3/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Test
    @Ignore
    public void insert(){
        UserInfo userInfo = new UserInfo();
        userInfo.setName("邓文杰");
        userInfo.setSex(SexConstant.MALE);
        userInfo.setAge(26);
        userInfo.setStatus(StatusConstant.NORMAL);
        userInfoRepository.save(userInfo);
    }

    @Test
    @Ignore
    public void test(){
        Page<UserInfo> page = userInfoRepository.findAllBySex(new PageRequest(0, 1), SexConstant.MALE);
        System.out.println();
    }

}
