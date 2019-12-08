package com.lxg.member;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberMapper {
    @Insert("insert into user values(null,#{name},#{age});")
    public int addUser(@Param("name") String name, @Param("age") Integer age);
}
