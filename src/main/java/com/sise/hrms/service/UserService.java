package com.sise.hrms.service;

import com.sise.hrms.constant.RoleConstant;
import com.sise.hrms.excep.PwdNotRepeatException;
import com.sise.hrms.po.Role;
import com.sise.hrms.po.User;
import com.sise.hrms.repository.RoleRepository;
import com.sise.hrms.repository.UserRepository;
import com.sise.hrms.vo.ChangePwdVo;
import com.sise.hrms.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by holyfrans on 2017/3/6.
 * 用户业务
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public User findOneUser(Integer id){
        return userRepository.findOne(id);
    }

    public Page<User> findAllUserByPage(Integer page, Integer pageSize){
        return userRepository.findAll(new PageRequest(page - 1, pageSize));
    }

    public Page<User> findAllUserByRoleNotAdminAndPage(Integer page, Integer pageSize){
        Role role = roleRepository.findOneByRoleName(RoleConstant.ROLE_ADMIN);
        return userRepository.findAllByRoleNotOrRoleIsNull(new PageRequest(page - 1, pageSize), role);
    }

    public Page<User> findAllUserByRoleNotAdminAndUsernameLikeAndPage(Integer pageIndex, Integer pageSize, String username) {
        Role role = roleRepository.findOneByRoleName(RoleConstant.ROLE_ADMIN);
        return userRepository.findAllByRoleNotAndUsernameLike(new PageRequest(pageIndex - 1, pageSize), role, "%" + username + "%");
    }

    @Transactional
    public void changeUserPwd(ChangePwdVo changePwdVo) throws PwdNotRepeatException{
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        User user = userRepository.findOneByUsername(username);
        String oldPwd = changePwdVo.getOldPwd();
        String pwd = changePwdVo.getPassword();
        String rePwd = changePwdVo.getRepeatPwd();
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        if ((encoder.encodePassword(oldPwd, null)).equals(user.getPassword()) && pwd.equals(rePwd)) {
            user.setPassword(pwd);
        } else {
            throw new PwdNotRepeatException("密码和重复密码不一致");
        }
        saveUser(user);
    }

    @Transactional
    public User saveUserByUserVo(UserVo userVo){
        User user = null;
        if (userVo.getId() != null)
            user = findOneUser(userVo.getId());
        else
            user = new User();
        user.setUsername(userVo.getUsername());
        user.setRole(userVo.getRole());
        user.setUserInfo(userVo.getUserInfo());
        if (userVo.getPassword() != null && !userVo.getPassword().trim().equals("")){
            user.setPassword(userVo.getPassword());
            return saveUser(user);
        }
        return userRepository.save(user);
    }

    @Transactional
    public User saveUser(User user){
        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
        user.setPassword(md5PasswordEncoder.encodePassword(user.getPassword(),null));
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUserById(Integer id){
        User user = userRepository.findOne(id);
        user.setRole(null);
        user.setUserInfo(null);
        userRepository.delete(user);
    }

}
