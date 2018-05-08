package com.sise.hrms.repository;

import com.sise.hrms.po.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by acer on 2017/3/6.
 */
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    public Department findOneByDeptName(String deptName);
    public Page<Department> findAllByDeptNameLike(Pageable pageable, String deptName);
    public List<Department> findAllByIdNot(Integer id);
}
