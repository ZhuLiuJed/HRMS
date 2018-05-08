package com.sise.hrms.controller;

import com.sise.hrms.po.Department;
import com.sise.hrms.po.Position;
import com.sise.hrms.service.DepartmentService;
import com.sise.hrms.service.PositionService;
import com.sise.hrms.util.JsonDataUtil;
import com.sise.hrms.vo.MergePkVo;
import com.sise.hrms.vo.PkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by holyfrans on 2017/3/12.
 * 部门管理控制类
 */
@RestController
@RequestMapping(value = "/dept-mana")
public class DeptManagementController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PositionService positionService;

    @GetMapping(value = "/dept-all-list")
    public Map<String, Object> deptList(){
        Page<Department> page = departmentService.findAllDepartmentByPage(1, 1000);
        return JsonDataUtil.toLayUiMap(page);
    }
    @GetMapping(value = "/department-get")
    public Department    departmentGet(Integer id){
        return departmentService.findOneDepartment(id);
    }
    @GetMapping(value = "/position-get")
    public Position    positionGet(Integer id){
        return positionService.findOnePosition(id);
    }
    @GetMapping(value = "/dept-list")
    public Map<String, Object> DeptInfoList(Integer pageIndex, Integer pageSize, String type, String value){
        Page<Department> pages = null;
        if (type == null || type.equals("")){
            pages = departmentService.findAllDepartmentByPage(pageIndex, pageSize);
        } else if(type.equals("name"))
            pages = departmentService.findAllDepartmentByNameLikeAndPage(pageIndex, pageSize, value);
        return JsonDataUtil.toLayUiMap(pages);
    }
    @GetMapping(value = "/position-page-list")
    public Map<String, Object> positionInfoList(Integer pageIndex, Integer pageSize, String type, String value){
        Page<Position> pages = null;
        if (type == null || type.equals("")){
            pages = positionService.findAllPositionByPage(pageIndex, pageSize);
        } else if(type.equals("name"))
            pages = positionService.findAllPositionByNameLikeAndPage(pageIndex, pageSize, value);
        return JsonDataUtil.toLayUiMap(pages);
    }
    @PostMapping(value = "/position-add")
    public String positionInfoAdd(@Valid @RequestBody Position position) {
        positionService.savePosition(position);
        return "{success:true}";
    }

    @PutMapping(value = "/position-save")
    public String positionInfoSave(@Valid @RequestBody Position position){
        positionService.savePosition(position);
        return "{success:true}";
    }

    @DeleteMapping(value = "/position-delete")
    public String positionInfoDelete(@RequestBody PkVo pkVo){
        positionService.deletePositionById(pkVo.getId());
        return "{success:true}";
    }
    @GetMapping(value = "/position-list")
    public Map<String, Object> positionList(){
        Page<Position> page = positionService.findAllPositionByPage(1, 1000);
        return JsonDataUtil.toLayUiMap(page);
    }
    @PostMapping(value = "/department-add")
    public String departmentInfoAdd(@Valid @RequestBody Department department) {
        departmentService.saveDepartment(department);
        return "{success:true}";
    }

    @PostMapping(value = "/department-merge")
    public  String departmentMerge(@RequestBody MergePkVo mergePkVo){
        departmentService.mergeDepartment(mergePkVo.getFromId(), mergePkVo.getToId());
        return "{success:true}";
    }
    @PostMapping(value = "/department-notId-list")
    public Map<String,Object>departmentNotIdList(@RequestBody PkVo pkVo) {
        Map<String, Object> map = new HashMap<>();
        List<Department> list = departmentService.findAllDepartmentByIdNot(pkVo.getId());
        map.put("list", list);
        map.put("success", true);
        return map;
    }
    @PutMapping(value = "/department-save")
    public String departmentInfoSave(@Valid @RequestBody Department department){
        departmentService.saveDepartment(department);
        return "{success:true}";
    }

    @DeleteMapping(value = "/department-delete")
    public String departmentInfoDelete(@RequestBody PkVo pkVo){
        departmentService.deleteDepartment(pkVo.getId());
        return "{success:true}";
    }

}
