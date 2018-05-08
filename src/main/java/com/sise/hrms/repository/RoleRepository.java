package com.sise.hrms.repository;

import com.sise.hrms.po.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by acer on 2017/3/6.
 */
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Page<Role> findAllByRoleNameLike(Pageable pageable, String roleName);
    public Role findOneByRoleName(String roleName);
}
