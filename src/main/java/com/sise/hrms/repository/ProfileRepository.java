package com.sise.hrms.repository;

import com.sise.hrms.po.Profile;
import com.sise.hrms.po.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * Created by acer on 2017/3/6.
 */
public interface ProfileRepository extends JpaRepository<Profile,Integer> {
    Page<Profile> findAllByProfileName(Pageable pageRequest, String profileName);
    Page<Profile> findAllByUserInfoIn(Pageable pageRequest, Collection<UserInfo> userInfos);
    public Page<Profile> findAllByProfileNameLike(Pageable pageable, String trainingType);
}
