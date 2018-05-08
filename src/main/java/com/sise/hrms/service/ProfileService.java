package com.sise.hrms.service;

import com.sise.hrms.po.Profile;
import com.sise.hrms.po.UserInfo;
import com.sise.hrms.repository.ProfileRepository;
import com.sise.hrms.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by holyfrans on 2017/3/9.
 * 个人档案业务
 */
@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;

    public Page<Profile> findAllProfileByPage(Integer page, Integer pageSize){
        return profileRepository.findAll(new PageRequest(page - 1, pageSize));
    }
    public Page<Profile> findAllProfileByProfileNameLike(Integer page, Integer pageSize, String profileName){
        return profileRepository.findAllByProfileNameLike(new PageRequest(page - 1, pageSize), "%" + profileName + "%");
    }
    public Page<Profile> findAllProfileByProfileNameAndPage(Integer page, Integer pageSize, String profileName){
        return profileRepository.findAllByProfileName(new PageRequest(page - 1, pageSize), profileName);
    }

    public Page<Profile> findAllProfileByUnameAndPage(Integer page, Integer pageSize, String uname){
        Page<UserInfo> userInfos = userInfoRepository.findAllByNameLike(new PageRequest(0, 1000),"%" + uname + "%");
        return profileRepository.findAllByUserInfoIn(new PageRequest(page - 1, pageSize), userInfos.getContent());
    }
    public Profile findOneProfile(Integer id){
        return  profileRepository.findOne(id);
    }
    @Transactional
    public Profile saveProfile(Profile profile){
        return profileRepository.save(profile);
    }

    @Transactional
    public void deleteProfileById(Integer id){
        profileRepository.delete(id);
    }
}
