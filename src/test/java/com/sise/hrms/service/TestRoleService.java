package com.sise.hrms.service;

import com.sise.hrms.constant.AuthConstant;
import com.sise.hrms.constant.RoleConstant;
import com.sise.hrms.po.Auth;
import com.sise.hrms.po.Role;
import com.sise.hrms.repository.AuthRepository;
import com.sise.hrms.repository.RoleRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by holyfrans on 2017/3/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.DEFAULT)
public class TestRoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthRepository authRepository;

    @Test
    public void test() {
        Role role = new Role();
        Auth auth = authRepository.findOneByAuthority(AuthConstant.AUTH_ADMIN);
        List<Auth> list = new ArrayList<>();
        list.add(auth);
        role.setAuths(list);
        role.setRoleName("你爸爸");
        roleRepository.save(role);
        Page<Role> page;
        page = roleRepository.findAllByRoleNameLike(new PageRequest(0, 1), AuthConstant.AUTH_ADMIN);
    }
}