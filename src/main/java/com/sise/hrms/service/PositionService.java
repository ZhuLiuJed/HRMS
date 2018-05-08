package com.sise.hrms.service;

import com.sise.hrms.po.Department;
import com.sise.hrms.po.Position;
import com.sise.hrms.po.UserInfo;
import com.sise.hrms.repository.PositionRepository;
import com.sise.hrms.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by holyfrans on 2017/3/9.
 */
@Service
public class PositionService {
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;

    public List<Position> findAllPosition(){
        return positionRepository.findAll();
    }

    public Page<Position> findAllPositionByPage(Integer page, Integer pageSize){
        return positionRepository.findAll(new PageRequest(page - 1, pageSize));
    }
    public Page<Position> findAllPositionByNameLikeAndPage(Integer page, Integer pageSize, String name) {
        return positionRepository.findAllByPositionNameLike(new PageRequest(page - 1, pageSize), "%" + name + "%");
    }

    public Position findOnePosition(Integer id) {
        return  positionRepository.findOne(id);
    }
    @Transactional
    public Position savePosition(Position position){
        return positionRepository.save(position);
    }

    @Transactional
    public void deletePositionById(Integer id){
        Position position = positionRepository.findOne(id);
        List<UserInfo> list = userInfoRepository.findAllByPosition(position);
        for (UserInfo userInfo : list){
            userInfo.setPosition(null);
        }
        userInfoRepository.save(list);
        positionRepository.delete(id);
    }


}
