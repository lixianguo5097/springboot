package com.lxg.service;


import com.lxg.common.PageResult;
import com.lxg.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author XIANGUO LI
 */
public interface UserService {

	/**
	 * 新增
	 *
	 * @param user
	 */
	public void addUser(User user);

	/**
	 * 修改
	 * @param user
	 */
	public void updateUser(User user);

	/**
	 * 根据id删除
	 *
	 * @param id
	 */
	public void deleteUser(String id) ;

	/**
	 * 查询所有
	 */
	public List<User> findAll();

	/**
	 * 根据id查询
	 *
	 * @param id
	 */
	public User findUserById(String id);

    /**
     * 条件查询+age排序
     * @param
     */
	public List<User> findSearch(Map searchMap);

    /**
     * 条件+分页+age排序
     */
	public PageResult<User> findSearch(Map searchMap, int page, int size);

	/**
	 * 通过Xml查映射文件查询
	 * @return
	 */
	public List<User> findByXml();

}
