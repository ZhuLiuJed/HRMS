package com.sise.hrms.controller;

import com.sise.hrms.po.Training;
import com.sise.hrms.po.TrainingRecord;
import com.sise.hrms.po.UserInfo;
import com.sise.hrms.service.TrainingRecordService;
import com.sise.hrms.service.TrainingService;
import com.sise.hrms.service.UserInfoService;
import com.sise.hrms.util.JsonDataUtil;
import com.sise.hrms.vo.PkVo;
import com.sise.hrms.vo.TimeVo;
import com.sise.hrms.vo.TrainingAndUserInfoListVo;
import com.sise.hrms.vo.TrainingAndUserInfoPkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * Created by holyfrans on 2017/3/13.
 * 教育培训控制类
 */
@RestController
@RequestMapping(value = "/training")
public class TrainingController {
    @Autowired
    private TrainingService trainingService;
    @Autowired
    private TrainingRecordService trainingRecordService;
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping(value = "/training-get")
    public Training TrainingGet(Integer id){
        return trainingService.findOneTraining(id);
    }
    @GetMapping(value = "/training-list")
    public Map<String, Object>TrainingInfoList(Integer pageIndex, Integer pageSize, String type, String value){
        Page<Training> pages = null;
        if (type == null || type.equals("")){
            pages = trainingService.findAllTrainingByPage(pageIndex, pageSize);
        } else if(type.equals("name"))
            pages = trainingService.findAllTrainingByTypeLikeAndPage(pageIndex, pageSize,value);
        else if (type.equals("beginTime"))
            pages = trainingService.findAllTrainingByBeginTimeBetween(pageIndex,pageSize,value);
        else if (type.equals("endTime"))
            pages = trainingService.findAllTrainingByEndTimeBetween(pageIndex,pageSize,value);
        else if (type.equals("after"))
            pages = trainingService.findAllTrainingByEndTimeAfter(pageIndex,pageSize);
        return JsonDataUtil.toLayUiMap(pages);
    }
    @PostMapping(value = "/training-add")
    public String TrainingInfoAdd(@Valid @RequestBody Training training) {
        trainingService.saveTraining(training);
        return "{success:true}";
    }

    @PutMapping(value = "/training-save")
    public String TrainingInfoSave(@Valid @RequestBody Training training){
        trainingService.saveTraining(training);
        return "{success:true}";
    }

    @DeleteMapping(value = "/training-delete")
    public String TrainingInfoDelete(@RequestBody PkVo pkVo){
        trainingService.deleteTrainingById(pkVo.getId());
        return "{success:true}";
    }

    @GetMapping(value = "/training-record-list")
    public Map<String, Object> trainingRecordList(Integer pageIndex, Integer pageSize, String type, String value){
        Map<String, Object> map = new HashMap<>();
        List<TrainingAndUserInfoListVo> voList = trainingRecordService.findAllTrainingAndUserInfoListVo();
        map.put("list", voList);
        map.put("count", voList.size());
        map.put("code", 0);
        return map;
    }

    @GetMapping(value = "/not-training-userinfos")
    public List<UserInfo> notTrainingUserInfos(Integer id){
        return userInfoService.findAllUserInfoByTrainingNot(id);
    }

    @GetMapping(value = "/training-record-one")
    public TrainingRecord trainingRecordOne(Integer id){
        return trainingRecordService.findOneTrainingRecord(id);
    }

    @PostMapping(value = "/training-record-add")
    public String trainingRecordAdd(@RequestBody TrainingAndUserInfoPkVo vo){
        trainingRecordService.saveTrainingRecordByVo(vo);
        return "{success:true}";
    }

    @PutMapping(value = "/training-record-update")
    public String trainingRecordUpdate(@RequestBody TrainingRecord trainingRecord){
        trainingRecordService.saveTrainingRecord(trainingRecord);
        return "{success:true}";
    }

    @DeleteMapping(value = "/training-record-delete")
    public String trainingRecordDelete(@RequestBody TrainingAndUserInfoPkVo vo){
        trainingRecordService.deleteTrainingRecordByVo(vo);
        return "{success:true}";
    }

}
