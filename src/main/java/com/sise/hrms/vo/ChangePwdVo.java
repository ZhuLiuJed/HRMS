package com.sise.hrms.vo;

/**
 * Created by holyfrans on 2017/3/9.
 */
public class ChangePwdVo {
    private String username;
    private String oldPwd;
    private String password;
    private String repeatPwd;

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
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

    public String getRepeatPwd() {
        return repeatPwd;
    }

    public void setRepeatPwd(String repeatPwd) {
        this.repeatPwd = repeatPwd;
    }
}
