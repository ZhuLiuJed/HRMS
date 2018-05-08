package com.sise.hrms.service;

import com.sise.hrms.constant.StatusConstant;
import com.sise.hrms.po.*;
import com.sise.hrms.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by holyfrans on 2017/3/8.
 * 用户信息业务逻辑
 */
@Service
public class UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private TrainingRecordRepository trainingRecordRepository;

    public UserInfo findOneUserInfo(Integer id){
        return userInfoRepository.findOne(id);
    }

    public Page<UserInfo> findAllUserInfoByPage(Integer page, Integer pageSize){
        return userInfoRepository.findAll(new PageRequest(page - 1, pageSize));
    }

    public Page<UserInfo> findAllUserInfoByNameLikeAndPage(Integer page, Integer pageSize, String name){
        return userInfoRepository.findAllByNameLike(new PageRequest(page - 1, pageSize), "%" + name + "%");
    }
    public  Page<UserInfo>findAllByDeptIsNullOrPositionIsNull(Integer page, Integer pageSize){
        return userInfoRepository.findAllByDeptIsNullOrPositionIsNull(new PageRequest(page-1,pageSize));
    }

    public Page<UserInfo> findAllUserInfoByNameAndPage(Integer page, Integer pageSize, String name){
        return userInfoRepository.findAllByName(new PageRequest(page - 1, pageSize), name);
    }

    public Page<UserInfo> findAllUserInfoBySexAndPage(Integer page, Integer pageSize, String sex){
        return userInfoRepository.findAllBySex(new PageRequest(page - 1, pageSize), sex);
    }
    public  Page<UserInfo> findAllByJoinedDateAfter(Integer page, Integer pageSize,Date minTime){
        return  userInfoRepository.findAllByJoinedDateAfter(new PageRequest(page-1,pageSize),minTime);
    }
    public  Page<UserInfo> findAllByJoinedDateAfterAndByNameLike(Integer page, Integer pageSize,String name){
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,-30);
        Date date1 = calendar.getTime();
        return  userInfoRepository.findAllByJoinedDateAfterAndNameLike(new PageRequest(page-1,pageSize),date1, "%" + name + "%");
    }

    public Page<UserInfo> findAllUserInfoByDeptAndPage(Integer page,Integer pageSize, String deptName){
        Department dept = departmentRepository.findOneByDeptName(deptName);
        return userInfoRepository.findAllByDept(new PageRequest(page - 1, pageSize), dept);
    }

    public Page<UserInfo> findAllUserInfoByPositionAndPage(Integer page,Integer pageSize, String positionName){
        Position position = positionRepository.findOneByPositionName(positionName);
        return userInfoRepository.findAllByPosition(new PageRequest(page - 1, pageSize), position);
    }

    @Transactional
    public UserInfo saveUserInfo(UserInfo userInfo){
        if (userInfo.getId() == null) {
            userInfo.setJoinedDate(new Date());
            userInfo.setStatus(StatusConstant.NORMAL);
        } else {
            UserInfo saveUserInfo = userInfoRepository.findOne(userInfo.getId());
            saveUserInfo.setAge(userInfo.getAge());
            saveUserInfo.setName(userInfo.getName());
            saveUserInfo.setSex(userInfo.getSex());
            saveUserInfo.setEducation(userInfo.getEducation());
            saveUserInfo.setPhone(userInfo.getPhone());
            saveUserInfo.setEmail(userInfo.getEmail());
            return userInfoRepository.save(saveUserInfo);
        }
        return userInfoRepository.save(userInfo);
    }

    @Transactional
    public void deleteUserInfoById(Integer id){
        UserInfo userInfo = userInfoRepository.findOne(id);
        List<TrainingRecord> list = trainingRecordRepository.findAllByUserInfo(userInfo);
        for (TrainingRecord trainingRecord : list){
            trainingRecordRepository.delete(trainingRecord);
        }
        userInfoRepository.delete(id);
    }

    public List<UserInfo> findAllUserInfoByUserIsNull() {
        return userInfoRepository.findAllByUserIsNull();
    }

    public List<UserInfo> findAllUserInfoByContractIsNull(){return  userInfoRepository.findAllByContractIsNull();}

    public List<UserInfo> findAllUserInfoByTrainingNot(Integer id) {
        Training training = trainingRepository.findOne(id);
        List<TrainingRecord> list = trainingRecordRepository.findAllByTraining(training);
        List<Integer> notInId = new ArrayList<>();
        for (TrainingRecord tr : list){
            notInId.add(tr.getUserInfo().getId());
        }
        if (notInId.size() > 0)
            return userInfoRepository.findAllByIdNotIn(notInId);
        else
            return userInfoRepository.findAll();
    }
    public  Page<UserInfo>findAllByDeptInAndPage(Integer page, Integer pageSize, String name){
        Page<Department> departments = departmentRepository.findAllByDeptNameLike(new PageRequest(page-1,pageSize), "%" + name + "%");
        return  userInfoRepository.findAllByDeptIn(new PageRequest(page-1,pageSize),departments.getContent());
    }
    public  Page<UserInfo>findAllByPositionInAndPage(Integer page, Integer pageSize, String name){
        Page<Position> positions =positionRepository.findAllByPositionNameLike(new PageRequest(page-1,pageSize), "%" + name + "%");
        return  userInfoRepository.findAllByPositionIn(new PageRequest(page-1,pageSize),positions.getContent());
    }
}
