package com.sise.hrms.service;

import com.sise.hrms.po.Profile;
import com.sise.hrms.po.UserInfo;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by holyfrans on 2017/3/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.DEFAULT)
public class TestProfileService {
    @Autowired
    private ProfileService profileService;

    @Test
    public void testFindAllProfileByPage(){
        Page result = profileService.findAllProfileByPage(1, 1);
        System.out.println(result.getContent());
    }
    @Test
    public void testFindAllProfileByProfileNameAndPage(){
        Page result = profileService.findAllProfileByProfileNameAndPage(1, 1, "1");
        System.out.println(result.getContent());
    }
    @Test
    public void testFindAllProfileByUnameAndPage(){
        Page result = profileService.findAllProfileByUnameAndPage(1, 1, "张三");
        System.out.println(result.getContent());
    }
    @Test
    public void testSaveTraining(){
        Profile profile = new Profile();
        profile.setId(5);
        profile.setProfileName("new");
        profileService.saveProfile(profile);
    }
    @Test
    public void testDeleteTrainingById(){
        profileService.deleteProfileById(4);
    }
}
