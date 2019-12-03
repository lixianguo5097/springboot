package com.lxg.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lxg.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户Mapper
 * @author XIANGUO LI
 * @date 2019-10-31 11:42
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 方法名与映射文件的id一致
     */
    List<User> findByXml();
}
