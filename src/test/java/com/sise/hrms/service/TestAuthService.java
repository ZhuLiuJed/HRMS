package com.sise.hrms.service;

import com.sise.hrms.constant.AuthConstant;
import com.sise.hrms.constant.RoleConstant;
import com.sise.hrms.po.Auth;
import com.sise.hrms.po.Role;
import com.sise.hrms.repository.AuthRepository;
import com.sise.hrms.repository.RoleRepository;
import org.hibernate.criterion.Example;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by holyfrans on 2017/3/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.DEFAULT)
public class TestAuthService {
    @Autowired
    private AuthService authService;
    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    @Ignore
    public void test(){
        Auth auth1 = new Auth();
        auth1.setAuthority(AuthConstant.AUTH_PPRO);
        Auth auth2 = new Auth();
        auth2.setAuthority(AuthConstant.AUTH_DEPTM);
        Auth auth3 = new Auth();
        auth3.setAuthority(AuthConstant.AUTH_EDTRA);
        Auth auth4 = new Auth();
        auth4.setAuthority(AuthConstant.AUTH_PDEP);
        authService.saveOneAuth(auth1);
        authService.saveOneAuth(auth2);
        authService.saveOneAuth(auth3);
        authService.saveOneAuth(auth4);
    }

    @Test
    @Ignore
    public void test2(){
        Auth auth1 = authRepository.findOneByAuthority(AuthConstant.AUTH_PPRO);
        Auth auth2 = authRepository.findOneByAuthority(AuthConstant.AUTH_EDTRA);
        Auth auth3 = authRepository.findOneByAuthority(AuthConstant.AUTH_DEPTM);
        Auth auth4 = authRepository.findOneByAuthority(AuthConstant.AUTH_PDEP);

        Role role1 = new Role();
        role1.setRoleName(RoleConstant.ROLE_ORDINARY);
        List<Auth> list1 = new ArrayList<Auth>();
        list1.add(auth1);
        list1.add(auth3);
        role1.setAuths(list1);
        roleRepository.save(role1);

        Role role2 = new Role();
        role2.setRoleName(RoleConstant.ROLE_MANAGER);
        List<Auth> list2 = new ArrayList<Auth>();
        list2.add(auth1);
        list2.add(auth2);
        list2.add(auth3);
        list2.add(auth4);
        role2.setAuths(list2);
        roleRepository.save(role2);

        Role role3 = new Role();
        role3.setRoleName(RoleConstant.ROLE_ADMIN);
        role3.setAuths(list2);
        roleRepository.save(role3);

    }
}
