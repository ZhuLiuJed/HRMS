package com.sise.hrms.vo;

import com.sise.hrms.po.Role;
import com.sise.hrms.po.UserInfo;

/**
 * Created by holyfrans on 2017/3/17.
 */
public class UserVo {
    private Integer id;
    private String username;
    private String password;
    private UserInfo userInfo;
    private Role role;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
