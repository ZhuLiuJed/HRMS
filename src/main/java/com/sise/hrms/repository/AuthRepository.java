package com.sise.hrms.repository;

import com.sise.hrms.po.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by acer on 2017/3/6.
 */
public interface AuthRepository extends JpaRepository<Auth,Integer> {
    public Auth findOneByAuthority(String authority);
}
