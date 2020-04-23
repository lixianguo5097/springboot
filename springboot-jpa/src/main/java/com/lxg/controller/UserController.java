package com.lxg.controller;

import com.lxg.common.PageResult;
import com.lxg.common.Result;
import com.lxg.model.User;
import com.lxg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户controller
 * @author LXG
 * @date 2019-10-28
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 新增
     * @param user 用户
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result addUser(@RequestBody User user) {
        userService.saveUser(user);
        return Result.success();
    }

    /**
     * 修改
     * @param user
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Result updateUser(@RequestBody User user) {
        if (user.getId() == null || "".equals(user.getId())) {
            return Result.fail("无id,更新失败");
        }
        userService.saveUser(user);
        return Result.success();
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        userService.deleteUser(id);
        return Result.success();
    }

    /**
     * 查询所有
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAllUser() {
        return userService.findAll();
    }

    /**
     * 根据id查询
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findByUserId(@PathVariable String id) {
        return Result.success(userService.findUserById(id));
    }

    /**
     * 条件查询
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap){
        return Result.success(userService.findSearch(searchMap));
    }

    /**
     * 条件+分页
     * @param searchMap
     * @param page
     * @param size
     */
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap,@PathVariable int page,@PathVariable int size){
        Page<User> pageBean = userService.findSearch(searchMap, page, size);
        return Result.success(new PageResult<>(pageBean.getTotalElements(),pageBean.getContent()) );
    }
}
