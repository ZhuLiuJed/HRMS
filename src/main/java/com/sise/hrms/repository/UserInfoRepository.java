package com.sise.hrms.repository;

import com.sise.hrms.po.Department;
import com.sise.hrms.po.Position;
import com.sise.hrms.po.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by acer on 2017/3/6.
 */
public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {
    public List<UserInfo> findAllByName(String name);
    public Page<UserInfo> findAllByName(Pageable page, String name);
    public Page<UserInfo> findAllByNameLike(Pageable page, String name);
    public Page<UserInfo> findAllBySex(Pageable page, String sex);
    public Page<UserInfo> findAllByDept(Pageable page, Department dept);
    public List<UserInfo> findAllByPosition(Position position);
    public Page<UserInfo> findAllByPosition(Pageable page, Position position);
    public List<UserInfo> findAllByUserIsNull();
    public List<UserInfo> findAllByContractIsNull();
    public Page<UserInfo> findAllByDeptIsNullOrPositionIsNull(Pageable page);
//    查询新进员工
    public Page<UserInfo> findAllByJoinedDateAfter(Pageable pageable,Date minTime);
    public Page<UserInfo> findAllByJoinedDateAfterAndNameLike(Pageable pageable,Date minTime,String name);
    public List<UserInfo> findAllByIdNotIn(List<Integer> id);
//    根据部门
    public Page<UserInfo> findAllByDeptIn(Pageable page, List<Department> dept);
    public Page<UserInfo> findAllByPositionIn(Pageable page, List<Position> position);
}
