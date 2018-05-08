package com.sise.hrms.controller;

import com.sise.hrms.po.Department;
import com.sise.hrms.po.Position;
import com.sise.hrms.po.UserInfo;
import com.sise.hrms.service.DepartmentService;
import com.sise.hrms.service.PositionService;
import com.sise.hrms.service.UserInfoService;
import com.sise.hrms.util.JsonDataUtil;
import com.sise.hrms.vo.PkVo;
import com.sise.hrms.vo.SprogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * Created by holyfrans on 2017/3/13.
 * 人事调配控制类
 */
@RestController
@RequestMapping(value = "/personnel-deployment")
public class PersonnelDeploymentController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private DepartmentService departmentService;
    @GetMapping(value = "/userinfo-all-list")
    public Map<String, Object> userInfoAllList(){
        Page<UserInfo> pages = null;
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,-30);
        Date date1 = calendar.getTime();
        pages =  userInfoService.findAllByJoinedDateAfter(1,1000,date1);
        return  JsonDataUtil.toLayUiMap(pages);
    }
    @GetMapping(value = "/userinfo-list")    public Map<String, Object> userInfoList(Integer pageIndex, Integer pageSize, String type, String value){
        Page<UserInfo> pages = null;
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        if (type == null || type.equals("")){
            calendar.setTime(date);
            calendar.add(calendar.DATE,-30);
            Date date1 = calendar.getTime();
            pages = userInfoService.findAllByJoinedDateAfter(pageIndex, pageSize,date1);
        } else if(type.equals("name")){
            pages = userInfoService.findAllByJoinedDateAfterAndByNameLike(pageIndex, pageSize,value);
        } else if(type.equals("time_7")){
            calendar.setTime(date);
            calendar.add(calendar.DATE,-7);
            Date date1 = calendar.getTime();
            pages = userInfoService.findAllByJoinedDateAfter(pageIndex, pageSize,date1);
        }else if(type.equals("time_15")){
            calendar.setTime(date);
            calendar.add(calendar.DATE,-15);
            Date date1 = calendar.getTime();
            pages = userInfoService.findAllByJoinedDateAfter(pageIndex, pageSize,date1);
        }
        return JsonDataUtil.toLayUiMap(pages);
    }
    @GetMapping(value = "/transfer-list")    public Map<String, Object> userInfotransferList(Integer pageIndex, Integer pageSize, String type, String value){
        Page<UserInfo> pages = null;
        if (type == null || type.equals("")){
            pages = userInfoService.findAllUserInfoByPage(pageIndex, pageSize);
        } else if(type.equals("name")){
            pages = userInfoService.findAllUserInfoByNameLikeAndPage(pageIndex, pageSize,value);
        }else if(type.equals("none_DP")){
            pages = userInfoService.findAllByDeptIsNullOrPositionIsNull(pageIndex,pageSize);
        }else if(type.equals("deptname")){
            pages = userInfoService.findAllByDeptInAndPage(pageIndex, pageSize, value);
        }else if(type.equals("positionname")){
            pages = userInfoService.findAllByPositionInAndPage(pageIndex, pageSize, value);
        }
        return JsonDataUtil.toLayUiMap(pages);
    }
    @GetMapping(value = "/userinfo-get")
    public UserInfo userInfoGet(Integer id){
        return userInfoService.findOneUserInfo(id);
    }

    @GetMapping(value = "/userinfo-ex-get")
    public Map<String, Object> userInfoExGet(@RequestParam(required = false) Integer id){
        Map<String, Object> map = new HashMap<>();
        if (id != null){
            UserInfo userInfo = userInfoService.findOneUserInfo(id);
            map.put("userInfo", userInfo);
        }
        List<Department> deptList = departmentService.findAllDepartment();
        List<Position> positionList = positionService.findAllPosition();
        map.put("deptList", deptList);
        map.put("positionList", positionList);
        return map;
    }
    @PutMapping(value = "/userinfo-save")
    public String userInfoSave(@Valid @RequestBody SprogVo sprogVo){
        UserInfo userInfo = userInfoService.findOneUserInfo(sprogVo.getId());
        Department department = departmentService.findOneDepartment(sprogVo.getDeptid());
        Position position = positionService.findOnePosition(sprogVo.getPositionid());
        userInfo.setDept(department);
        userInfo.setPosition(position);
        userInfoService.saveUserInfo(userInfo);
        return "{success:true}";
    }

    @DeleteMapping(value = "/userinfo-delete")
    public String userInfoDelete(@RequestBody PkVo pkVo){
        userInfoService.deleteUserInfoById(pkVo.getId());
        return "{success:true}";
    }
}
