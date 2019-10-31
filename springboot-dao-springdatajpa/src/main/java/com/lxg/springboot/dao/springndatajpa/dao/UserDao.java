package com.lxg.springboot.dao.springndatajpa.dao;

import com.lxg.springboot.dao.springndatajpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserDao  extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {
    @Modifying
    @Query("update User u set u.age =?2 where u.id =?1")
    void update(String id, Integer age);
}
