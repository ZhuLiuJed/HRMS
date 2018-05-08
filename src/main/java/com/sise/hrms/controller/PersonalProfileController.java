package com.sise.hrms.controller;

import com.sise.hrms.po.*;
import com.sise.hrms.service.*;
import com.sise.hrms.util.JsonDataUtil;
import com.sise.hrms.vo.ChangePwdVo;
import com.sise.hrms.vo.PkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by holyfrans on 2017/3/10.
 * 个人档案控制器
 */
@RestController
@RequestMapping(value = "/personal-profile")
public class PersonalProfileController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private ProfileService profileService;

    @GetMapping(value = "/userinfo-list")    public Map<String, Object> userInfoList(Integer pageIndex, Integer pageSize, String type, String value){
        Page<UserInfo> pages = null;
        if (type == null || type.equals("")){
            pages = userInfoService.findAllUserInfoByPage(pageIndex, pageSize);
        } else if(type.equals("name")){
            pages = userInfoService.findAllUserInfoByNameLikeAndPage(pageIndex, pageSize, value);
        }else if(type.equals("deptname")){
            pages = userInfoService.findAllByDeptInAndPage(pageIndex, pageSize, value);
        }else if(type.equals("positionname")){
            pages = userInfoService.findAllByPositionInAndPage(pageIndex, pageSize, value);
        }
        return JsonDataUtil.toLayUiMap(pages);
    }

    @GetMapping(value = "/userinfo-all-list")
    public  Map<String, Object> userInfoAllList(){
        Page<UserInfo> pages = null;
        pages =  userInfoService.findAllUserInfoByPage(1, 1000);
        return  JsonDataUtil.toLayUiMap(pages);
    }

    @GetMapping(value = "/userinfo-get")
    public UserInfo userInfoGet(Integer id){
        return userInfoService.findOneUserInfo(id);
    }


    @GetMapping(value = "/contract-ex-get")
    public Map<String, Object> contractExGet(@RequestParam(required = false) Integer id){
        Map<String, Object> map = new HashMap<>();
        if (id != null){
            Contract contract = contractService.findOneContract(id);
            map.put("contract", contract);
        }
        List<UserInfo> userInfos = userInfoService.findAllUserInfoByContractIsNull();
        map.put("userInfos", userInfos);
        return map;
    }
    @PostMapping(value = "/userinfo-add")
    public String userInfoAdd(@Valid @RequestBody UserInfo userInfo) {
        userInfoService.saveUserInfo(userInfo);
        return "{success:true}";
    }

    @PutMapping(value = "/userinfo-save")
    public String userInfoSave(@Valid @RequestBody UserInfo userInfo){
        userInfoService.saveUserInfo(userInfo);
        return "{success:true}";
    }

    @DeleteMapping(value = "/userinfo-delete")
    public String userInfoDelete(@RequestBody PkVo pkVo){
        userInfoService.deleteUserInfoById(pkVo.getId());
        return "{success:true}";
    }
    @GetMapping(value = "/userinfo_none_contract")
    public Map<String, Object> userinfoList(){
        Map<String, Object> map = new HashMap<>();
        List<UserInfo> userInfos = userInfoService.findAllUserInfoByContractIsNull();
        map.put("userInfos", userInfos);
        return map;
    }
    @GetMapping(value = "/contract-all-list")
    public Map<String, Object> contractList(){
        Page<Contract> page = contractService.findAllContractByPage(1, 1000);
        return JsonDataUtil.toLayUiMap(page);
    }
    @GetMapping(value = "/contract-get")
    public Contract   contractGet(Integer id){
        return contractService.findOneContract(id);
    }
    @GetMapping(value = "/contract-list")
    public Map<String, Object>contractInfoList(Integer pageIndex, Integer pageSize, String type, String value){
        Page<Contract> pages = null;
        if (type == null || type.equals("")){
            pages = contractService.findAllContractByPage(pageIndex, pageSize);
        } else if(type.equals("name"))
            pages = contractService.findAllContractByTypeLikeAndPage(pageIndex, pageSize,value);
        else if (type.equals("beginTime"))
            pages = contractService.findAllContractByTimeOfContractBetween(pageIndex,pageSize,value);
        else if (type.equals("endTime"))
            pages = contractService.findAllContractByExpirationTimeBetween(pageIndex,pageSize,value);
        else if (type.equals("after"))
            pages = contractService.findAllContractByExpirationTimeAfter(pageIndex,pageSize);
        return JsonDataUtil.toLayUiMap(pages);
    }
    @PostMapping(value = "/contract-add")
    public String contractInfoAdd(@Valid @RequestBody Contract contract) {
        contractService.saveContract(contract);
        return "{success:true}";
    }
    @PutMapping(value = "/contract-save")
    public String contractInfoSave(@Valid @RequestBody Contract contract){
        contractService.saveContract(contract);
        return "{success:true}";
    }
    @DeleteMapping(value = "/contract-delete")
    public String contractInfoDelete(@RequestBody PkVo pkVo){
        contractService.deleteContractById(pkVo.getId());
        return "{success:true}";
    }




    @GetMapping(value = "/profile-all-list")
    public Map<String, Object> profileList(){
        Page<Profile> page = profileService.findAllProfileByPage(1, 1000);
        return JsonDataUtil.toLayUiMap(page);
    }
    @GetMapping(value = "/profile-get")
    public Profile   profileGet(Integer id){
        return profileService.findOneProfile(id);
    }
    @GetMapping(value = "/profile-list")
    public Map<String, Object>profileInfoList(Integer pageIndex, Integer pageSize, String type, String value){
        Page<Profile> pages = null;
        if (type == null || type.equals("")){
            pages = profileService.findAllProfileByPage(pageIndex, pageSize);
        } else if(type.equals("p_name"))
            pages = profileService.findAllProfileByProfileNameLike(pageIndex,pageSize,value);
        else if(type.equals("u_name"))
            pages = profileService.findAllProfileByUnameAndPage(pageIndex,pageSize,value);
        return JsonDataUtil.toLayUiMap(pages);
    }
    @PostMapping(value = "/profile-add")
    public String profileInfoAdd(@Valid @RequestBody Profile profile) {
        profileService.saveProfile(profile);
        return "{success:true}";
    }
    @PutMapping(value = "/profile-save")
    public String profileInfoSave(@Valid @RequestBody Profile profile){
        profileService.saveProfile(profile);
        return "{success:true}";
    }
    @DeleteMapping(value = "/profile-delete")
    public String profileInfoDelete(@RequestBody PkVo pkVo){
        profileService.deleteProfileById(pkVo.getId());
        return "{success:true}";
    }
    @GetMapping(value = "/profile-ex-get")
    public Map<String, Object> profileExGet(@RequestParam(required = false) Integer id){
        Map<String, Object> map = new HashMap<>();
        if (id != null){
            Profile profile = profileService.findOneProfile(id);
            map.put("profile", profile);
        }
        Page<UserInfo> userInfos = userInfoService.findAllUserInfoByPage(1,1000);
        map.put("userInfos", userInfos.getContent());
        return map;
    }
}
