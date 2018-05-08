package com.sise.hrms.controller;

import com.sise.hrms.excep.PwdNotRepeatException;
import com.sise.hrms.po.Role;
import com.sise.hrms.po.User;
import com.sise.hrms.po.UserInfo;
import com.sise.hrms.service.RoleService;
import com.sise.hrms.service.UserInfoService;
import com.sise.hrms.service.UserService;
import com.sise.hrms.util.JsonDataUtil;
import com.sise.hrms.vo.ChangePwdVo;
import com.sise.hrms.vo.PkVo;
import com.sise.hrms.vo.RoleAndAuthsVo;
import com.sise.hrms.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by holyfrans on 2017/3/13.
 * 系统管理控制类
 */
@RestController
@RequestMapping(value = "/sys-manager")
public class SystemManagerController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;


    @PutMapping(value = "/change-pwd")
    public String changePwd(@RequestBody ChangePwdVo changePwdVo){
        try {
            userService.changeUserPwd(changePwdVo);
            return "{success:true}";
        } catch (PwdNotRepeatException e) {
            e.printStackTrace();
        }
        return "{success:false}";
    }

    @GetMapping(value = "/role-list")
    public Map<String, Object> roleList(Integer pageIndex, Integer pageSize, String type, String value){
        Page<Role> page = null;
        if (type == null || type.equals("")){
            page = roleService.findAllRoleByPage(pageIndex, pageSize);
        }else if(type.equals("name")){
            page = roleService.findAllRoleByRoleNameLikeAndPage(pageIndex, pageSize, value);
        }
        return JsonDataUtil.toLayUiMap(page);
    }

    @GetMapping(value = "/role-get")
    public Role roleOne(Integer id){
        return roleService.findOneRole(id);
    }

    @DeleteMapping(value = "/role-delete")
    public String roleDelete(@RequestBody PkVo pkVo){
        roleService.deleteRoleById(pkVo.getId());
        return "{success:true}";
    }

    @PostMapping(value = "/role-add")
    public String roleAdd(@RequestBody RoleAndAuthsVo roleAndAuthsVo){
        roleService.saveRoleAndAuthsList(roleAndAuthsVo);
        return "{success:true}";
    }

    @PutMapping(value = "/role-update")
    public String roleUpdate(@RequestBody RoleAndAuthsVo roleAndAuthsVo){
        roleService.saveRoleAndAuthsList(roleAndAuthsVo);
        return "{success:true}";
    }

    @GetMapping(value = "/user-one")
    public User userOne(Integer id){
        return userService.findOneUser(id);
    }

    @GetMapping(value = "/user-ex-get")
    public Map<String, Object> userExGet(@RequestParam(required = false) Integer id){
        Map<String, Object> map = new HashMap<>();
        if (id != null){
            User user = userService.findOneUser(id);
            map.put("user", user);
        }
        List<UserInfo> userInfoList = userInfoService.findAllUserInfoByUserIsNull();
        List<Role> roleList = roleService.findAllRole();
        map.put("userInfoList", userInfoList);
        map.put("roleList", roleList);
        return map;
    }

    @GetMapping(value = "/user-list")
    public Map<String, Object> userRoleList(Integer pageIndex, Integer pageSize, String type, String value){
        Page<User> page = null;
        if (type == null || type.equals("")) {
            page = userService.findAllUserByRoleNotAdminAndPage(pageIndex, pageSize);
        } else {
            page = userService.findAllUserByRoleNotAdminAndUsernameLikeAndPage(pageIndex, pageSize, value);
        }
        return JsonDataUtil.toLayUiMap(page);
    }

    @DeleteMapping(value = "/user-delete")
    public String userDelete(@RequestBody PkVo pkVo){
        userService.deleteUserById(pkVo.getId());
        return "{success:true}";
    }

    @PutMapping(value = "/user-update")
    public String userUpdate(@RequestBody UserVo user){
        userService.saveUserByUserVo(user);
        return "{success:true}";
    }

    @PostMapping(value = "/user-add")
    public String userAdd(@RequestBody UserVo user){
        userService.saveUserByUserVo(user);
        return "{success:true}";
    }

}
