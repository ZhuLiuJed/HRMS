package com.sise.hrms.service;

import com.sise.hrms.po.Auth;
import com.sise.hrms.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by acer on 2017/3/6.
 */
@Service
public class AuthService {
    @Autowired
    private AuthRepository authRepository;
    @Transactional
    public Auth saveOneAuth(Auth auth){
        return  authRepository.save(auth);
    }
    public Auth findOneAuth( Integer authId){
        return authRepository.findOne(authId);
    }
}
