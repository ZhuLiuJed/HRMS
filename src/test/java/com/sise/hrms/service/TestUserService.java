package com.sise.hrms.service;

import com.sise.hrms.excep.PwdNotRepeatException;
import com.sise.hrms.po.User;
import com.sise.hrms.vo.ChangePwdVo;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by holyfrans on 2017/3/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.DEFAULT)
public class TestUserService {

    @Autowired
    private UserService userService;
    private User user;
    private ChangePwdVo changePwdVo;
    @Before
    public void setup() {
    }
    @Test
    public void testSaveUser(){

        user =new User();
        user.setUsername("杰神");
        user.setPassword("1234567");
        userService.saveUser(user);
    }
    @Test
    @Ignore
    public void test()throws Exception{

        changePwdVo = new ChangePwdVo();
        changePwdVo.setUsername("杰神");
        changePwdVo.setPassword("131234");
        changePwdVo.setRepeatPwd("131234");
        userService.changeUserPwd(changePwdVo);
    }
    @Test(expected = PwdNotRepeatException.class)
    public void testErro()throws Exception{

        changePwdVo = new ChangePwdVo();
        changePwdVo.setUsername("杰神");
        changePwdVo.setPassword("1312345");
        changePwdVo.setRepeatPwd("131234");
        userService.changeUserPwd(changePwdVo);
    }

    @Test
    public void addRoot(){
        User root = new User();
        root.setUsername("root");
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        root.setPassword(encoder.encodePassword("123456", null));
        userService.saveUser(root);
    }

}
