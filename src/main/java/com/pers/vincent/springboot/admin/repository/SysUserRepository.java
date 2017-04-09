package com.pers.vincent.springboot.admin.repository;

import com.pers.vincent.springboot.admin.domain.SysUserModel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统管理员数据持久化层
 * 如果该接口中所有的操作都进行缓存，可以使用 @CacheConfig(cacheNames = "system:user") 注解
 * <p>
 * Created by IDEA.
 * User: vincent
 * Date 2017/4/9
 */
@Transactional
/* @CacheConfig(cacheNames = "system.user") */
public interface SysUserRepository extends JpaRepository<SysUserModel, String> {

    /**
     * 查询所有系统管理员，并将结果更新至缓存
     * 注解 @Cacheable 标记方法为被缓存的方法，当通过参数拼接出的 key 在缓存中找不到对应的 value 时，会调用该方法
     * 方法的返回值作为 value 记录到缓存数据库中。
     * 第一次查询，缓存中没有数据，redis 找不到 value，便会执行数据库的查询操作。
     * 然后将返回的系统管理员集合全部保存到缓存中，第二次再次查询，返回的结果是缓存中的数据。
     * 注意：缓存的对象必须实现序列化。
     *
     * @return 全部系统管理员
     */
    @Override
    @Cacheable(value = "system:user", keyGenerator = "wiselyKeyGenerator")
    List<SysUserModel> findAll();
}
