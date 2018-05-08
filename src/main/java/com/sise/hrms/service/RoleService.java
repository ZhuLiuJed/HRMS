package com.sise.hrms.service;

import com.sise.hrms.constant.RoleConstant;
import com.sise.hrms.po.Auth;
import com.sise.hrms.po.Role;
import com.sise.hrms.po.User;
import com.sise.hrms.repository.AuthRepository;
import com.sise.hrms.repository.RoleRepository;
import com.sise.hrms.repository.UserRepository;
import com.sise.hrms.vo.RoleAndAuthsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by holyfrans on 2017/3/9.
 */
@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Role> findAllRole(){
        return roleRepository.findAll();
    }

    public Page<Role> findAllRoleByPage(Integer page, Integer pageSize){
        Page<Role> pages = roleRepository.findAll(new PageRequest(page - 1, pageSize));
        return pages;
    }


    public Page<Role> findAllRoleByRoleNameLikeAndPage(Integer page, Integer pageSize, String roleName){
        Page<Role> pages = roleRepository.findAllByRoleNameLike(new PageRequest(page - 1, pageSize), "%" + roleName + "%");
        return pages;
    }

    @Transactional
    public Role saveRoleAndAuthsList(RoleAndAuthsVo roleAndAuthsVo){
        List<Auth> auths = new ArrayList<>();
        for (Auth auth : roleAndAuthsVo.getAuthorities()){
            auth = authRepository.findOneByAuthority(auth.getAuthority());
            if (auth != null)
                auths.add(auth);
        }
        Role role = new Role();
        role.setId(roleAndAuthsVo.getId());
        role.setRoleName(roleAndAuthsVo.getRoleName());
        role.setAuths(auths);
        return saveRole(role);
    }

    @Transactional
    public Role saveRole(Role role){
        return roleRepository.save(role);
    }

    @Transactional
    public void deleteRoleById(Integer id){
        Role role = roleRepository.findOne(id);
        List<User> list = userRepository.findAllByRole(role);
        for (User user : list){
            user.setRole(null);
        }
        userRepository.save(list);
        roleRepository.delete(id);
    }

    public Role findOneRole(Integer id) {
        return roleRepository.findOne(id);
    }
}
