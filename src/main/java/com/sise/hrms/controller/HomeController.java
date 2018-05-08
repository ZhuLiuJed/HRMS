package com.sise.hrms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by holyfrans on 2017/3/1.
 */
@Controller
public class HomeController {

    @GetMapping(value = "/index")
    public String index(){
        return "/index";
    }

    @GetMapping(value = "/main")
    public String mainHtml(){
        return "/main";
    }

    @GetMapping(value = "/login")
    public String login(){
        return "/login";
    }

    @GetMapping(value = "/error")
    public String error(){
        return "/error";
    }

    @GetMapping(value = "/personal-profile/change-password")
    public String changePassword(){
        return "/change-password";
    }

    @GetMapping(value = "/personal-profile/user-info")
    public String userinfo(){
        return "/user-info";
    }

    @GetMapping(value = "/personal-profile/contract-info")
    public String contractinfo(){
        return "/contract";
    }

    @GetMapping(value = "/dept-mana/department-info")
    public String Departmentinfo(){
        return "/Department-info";
    }
    @GetMapping(value = "/dept-mana/position")
    public String positioninfo(){
        return "/position";
    }

    @GetMapping(value = "/training/training")
    public String traininginfo(){
        return "/training";
    }
    @GetMapping(value = "/training/training-record")
    public String trainingRecord(){
        return "/training-record";
    }

    @GetMapping(value = "/personnel-deployment/transfer")
    public String transfer(){
        return "/transfer";
    }

    @GetMapping(value = "/personnel-deployment/sprog")
    public String sprog(){
        return "/sprog";
    }

    @GetMapping(value = "/sys-manager/user-mana")
    public String userMana(){
        return "/user-mana";
    }

    @GetMapping(value = "/sys-manager/role-mana")
    public String roleMana(){
        return "/role-mana";
    }
    @GetMapping(value = "/personal-profile/profile-info")
    public String profile(){
        return "/profile";
    }
}
