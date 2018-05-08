package com.sise.hrms.repository;

import com.sise.hrms.po.Training;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * Created by holyfrans on 2017/3/9.
 */
public interface TrainingRepository extends JpaRepository<Training, Integer>{
   public Page<Training> findAllByTypeLike(Pageable pageable, String trainingType);
   public Page<Training> findAllByBeginTimeBetween(Pageable pageable,Date minTime,Date MaxTime);
   public Page<Training> findAllByEndTimeBetween(Pageable pageable,Date minTime,Date MaxTime);
   public Page<Training> findAllByBeginTimeBeforeAndEndTimeAfter(Pageable pageable,Date before, Date after);
}
