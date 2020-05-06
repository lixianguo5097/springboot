package com.lxg.service;


import com.lxg.common.PageResult;
import com.lxg.model.MybatisUser;

import java.util.List;
import java.util.Map;

/**
 * @author LXG
 */
public interface UserService {

	/**
	 * 新增
	 *
	 * @param mybatisUser
	 */
	public void addUser(MybatisUser mybatisUser);

	/**
	 * 修改
	 * @param mybatisUser
	 */
	public void updateUser(MybatisUser mybatisUser);

	/**
	 * 根据id删除
	 *
	 * @param id
	 */
	public void deleteUser(String id) ;

	/**
	 * 查询所有
	 */
	public List<MybatisUser> findAll();

	/**
	 * 根据id查询
	 *
	 * @param id
	 */
	public MybatisUser findUserById(String id);

    /**
     * 条件查询+age排序
     * @param
     */
	public List<MybatisUser> findSearch(Map searchMap);

    /**
     * 条件+分页+age排序
     */
	public PageResult<MybatisUser> findSearch(Map searchMap, int page, int size);

	/**
	 * 通过Xml查映射文件查询
	 * @return
	 */
	public List<MybatisUser> findByXml();

}
