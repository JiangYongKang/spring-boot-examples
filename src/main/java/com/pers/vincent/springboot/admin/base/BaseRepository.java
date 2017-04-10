package com.pers.vincent.springboot.admin.base;

import com.pers.vincent.springboot.admin.domain.SysUserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IDEA.
 * User: e
 * Date: 2017/4/10
 * Comment: 数据层基类，定义公共的 CRUD 方法，其余模块的数据持久化层继承该基类。
 * 该基类继承自 JpaRepository 拥有该基类下的全部方法
 *
 * @NoRepositoryBean 确保 spring 不会在运行时期对其初始化。去掉该注解后，会抛出
 * org.springframework.beans.factory.BeanCreationException 异常。
 * 具体原因不明，资料解释与泛型擦除相关。
 * <p>
 * T: 基础数据模型，如：SysUserModel
 * ID：数据模型的ID。
 * <p>
 * FIXME: 泛型擦除
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    /**
     * 根据状态值查询对象集合
     *
     * @param status 状态值
     * @return 对象集合
     */
    List<T> findByStatus(Integer status);
}
