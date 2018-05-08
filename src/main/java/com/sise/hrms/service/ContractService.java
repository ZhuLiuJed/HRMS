package com.sise.hrms.service;

import com.sise.hrms.po.Contract;
import com.sise.hrms.po.UserInfo;
import com.sise.hrms.repository.ContractRepository;
import com.sise.hrms.repository.UserInfoRepository;
import com.sise.hrms.vo.TimeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Map;

/**
 * Created by holyfrans on 2017/3/9.
 */
@Service
public class ContractService {
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;

    public Contract findOneContract(Integer id){
        return contractRepository.findOne(id);
    }

    public Page<Contract>  findAllContractByPage(Integer page, Integer pageSize){
        return contractRepository.findAll(new PageRequest(page - 1, pageSize));
    }

    public Page<Contract> findAllContractByTypeAndPage(Integer page, Integer pageSize, String type){
        return contractRepository.findAllByType(new PageRequest(page - 1, pageSize), type);
    }

    public Page<Contract> findAllContractByTimeOfContractBetween(Integer page, Integer pageSize,String value){
        TimeVo timeVo = new TimeVo();
        timeVo.setValue(value);
        Map<String, Date> map = timeVo.getTimeMap();
        Date min = map.get("minDate");
        Date max = map.get("maxDate");
        return contractRepository.findAllByTimeOfContractBetween(new PageRequest(page - 1, pageSize), min, max );
    }
    public Page<Contract> findAllContractByExpirationTimeBetween(Integer page, Integer pageSize, String value){
        TimeVo timeVo = new TimeVo();
        timeVo.setValue(value);
        return contractRepository.findAllByExpirationTimeBetween(new PageRequest(page - 1, pageSize),timeVo.getTimeMap().get("minDate"),timeVo.getTimeMap().get("maxDate"));
    }
    public Page<Contract> findAllContractByExpirationTimeAfter(Integer page, Integer pageSize){
        Date date = new Date();
        return contractRepository.findAllByExpirationTimeAfter(new PageRequest(page - 1, pageSize),date);
    }
    @Transactional
    public Contract saveContract(Contract contract){
        Integer id = contract.getId();
        Integer userInfoId = contract.getUserInfo().getId();
        if (id == null){
            //save
            contractRepository.save(contract);
            UserInfo userInfo = userInfoRepository.findOne(userInfoId);
            userInfo.setContract(contract);
            userInfoRepository.save(userInfo);
        } else {
//            //update
            Contract oldContract = contractRepository.findOne(contract.getId());
            UserInfo oldUserInfo = oldContract.getUserInfo();
            oldUserInfo.setContract(null);
            userInfoRepository.save(oldUserInfo);
            //oldContract.setUserInfo(null);
            contract.setUserInfo(null);
            contractRepository.save(contract);
            UserInfo userInfo = userInfoRepository.findOne(userInfoId);
            userInfo.setContract(contract);
            userInfoRepository.save(userInfo);
        }
        return contract;
    }

    @Transactional
    public void deleteContractById(Integer id){
        UserInfo userInfo = contractRepository.findOne(id).getUserInfo();
        if (userInfo!=null){
            userInfo.setContract(null);
        }
        contractRepository.delete(id);
    }

    public Page<Contract> findAllContractByTypeLikeAndPage(Integer page, Integer pageSize, String type) {
        return contractRepository.findAllByTypeLike(new PageRequest(page - 1, pageSize), "%" + type + "%");
    }
}
