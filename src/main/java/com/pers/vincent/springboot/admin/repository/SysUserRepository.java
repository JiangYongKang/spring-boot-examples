package com.pers.vincent.springboot.admin.repository;

import com.pers.vincent.springboot.admin.domain.SysUserModel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IDEA.
 * User: vincent
 * Date 2017/4/9
 */
@Transactional
public interface SysUserRepository extends JpaRepository<SysUserModel, String> {

    @Override
    @Cacheable(value = "system:user")
    List<SysUserModel> findAll();
}
