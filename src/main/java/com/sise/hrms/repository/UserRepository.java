package com.sise.hrms.repository;

import com.sise.hrms.po.Role;
import com.sise.hrms.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by holyfrans on 2017/3/6.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findOneByUsername(String username);
    public Page<User> findAllByRoleNotOrRoleIsNull(Pageable pageable, Role role);
    public Page<User> findAllByRoleNotAndUsernameLike(Pageable pageRequest, Role role, String username);

    public List<User> findAllByRole(Role role);
}
