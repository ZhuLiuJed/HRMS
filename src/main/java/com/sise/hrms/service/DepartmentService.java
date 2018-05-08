package com.sise.hrms.service;

import com.sise.hrms.po.Department;
import com.sise.hrms.po.UserInfo;
import com.sise.hrms.repository.DepartmentRepository;
import com.sise.hrms.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by acer on 2017/3/8.
 */
@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    public Department findOneDepartment(Integer id){return departmentRepository.findOne(id);}
    @Transactional
    public Department saveDepartment (Department department){return  departmentRepository.save(department);}
    @Transactional
    public void deleteDepartment(Integer id){
        List<UserInfo> userInfoList = findOneDepartment(id).getUserInfos();
        for (UserInfo userInfo:userInfoList) {
            userInfo.setDept(null);
        }
        departmentRepository.delete(id);
    }
    @Transactional
    public  void mergeDepartment (Integer from_id,Integer to_id){
        List<UserInfo> userInfoList = findOneDepartment(from_id).getUserInfos();
        Department to_Department = findOneDepartment(to_id);
        for (UserInfo userInfo:userInfoList) {
            userInfo.setDept(to_Department);
        }
        userInfoRepository.save(userInfoList);
        departmentRepository.delete(from_id);
    }
    public List<Department> findAllDepartment(){
        return departmentRepository.findAll();
    }
    public Page<Department> findAllDepartmentByPage(Integer page, Integer pageSize){return departmentRepository.findAll(new PageRequest(page-1, pageSize));}

    public Page<Department> findAllDepartmentByNameLikeAndPage(Integer page, Integer pageSize, String name) {
        return departmentRepository.findAllByDeptNameLike(new PageRequest(page - 1, pageSize), "%" + name + "%");
    }
    public List<Department>findAllDepartmentByIdNot(Integer id){
        return  departmentRepository.findAllByIdNot(id);
    }
}
