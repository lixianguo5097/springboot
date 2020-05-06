package com.lxg.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lxg.model.MybatisUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户Mapper
 * @author LXG
 * @date 2019-10-31
 */
@Repository
public interface MyBatisUserMapper extends BaseMapper<MybatisUser> {
    /**
     * 方法名与映射文件的id一致
     */
    List<MybatisUser> findByXml();
}
