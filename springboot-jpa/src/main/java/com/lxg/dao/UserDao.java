package com.lxg.dao;

import com.lxg.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author XIANGUO LI
 */
public interface UserDao  extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {
    /**
     * 更新
     * @param id
     * @param age
     */
    @Modifying
    @Query("update User u set u.age =?2 where u.id =?1")
    void update(String id, Integer age);
}
