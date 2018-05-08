package com.sise.hrms.repository;

import com.sise.hrms.po.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * Created by holyfrans on 2017/3/9.
 */
public interface ContractRepository extends JpaRepository<Contract, Integer> {
    public Page<Contract> findAllByType(Pageable pageRequest, String type);
    public Page<Contract> findAllByTypeLike(Pageable pageable, String ContractType);
    public Page<Contract> findAllByTimeOfContractBetween(Pageable pageable, Date minTime, Date MaxTime);
    public Page<Contract> findAllByExpirationTimeBetween(Pageable pageable,Date minTime,Date MaxTime);
    public Page<Contract> findAllByExpirationTimeAfter(Pageable pageable,Date minTime);
}
