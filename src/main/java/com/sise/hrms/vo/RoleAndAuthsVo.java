package com.sise.hrms.vo;

import com.sise.hrms.po.Auth;
import com.sise.hrms.po.Role;

import java.util.List;

/**
 * Created by holyfrans on 2017/3/16.
 */
public class RoleAndAuthsVo {
    private Integer id;
    private String roleName;
    private List<Auth> authorities;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Auth> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Auth> authorities) {
        this.authorities = authorities;
    }
}
