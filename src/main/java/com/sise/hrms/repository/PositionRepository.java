package com.sise.hrms.repository;

import com.sise.hrms.po.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by acer on 2017/3/6.
 */
public interface PositionRepository extends JpaRepository<Position,Integer> {
    public Position findOneByPositionName(String positionName);
    public  Page<Position> findAllByPositionNameLike(Pageable pageable, String positionName);
}
