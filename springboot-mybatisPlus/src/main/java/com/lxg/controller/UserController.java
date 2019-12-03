package com.lxg.controller;


import com.lxg.common.StatusCode;
import com.lxg.common.Result;
import com.lxg.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lxg.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * 测试controller
 * @author XIANGUO LI
 * @date 2019-10-31 16:17
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 新增
     * @param user
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result addUser(@RequestBody User user) {
        userService.addUser(user);
        return new Result(true, StatusCode.OK,"新增成功");
    }

    /**
     * 根据id修改
     * @param user
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Result updateUser(@RequestBody User user) {
        if (user.getId() == null || user.getId().equals("")) {
            return new Result(false, StatusCode.ERROR,"无id,更新失败");
        }
        userService.updateUser(user);
        return new Result(true, StatusCode.OK,"更新成功");
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        userService.deleteUser(id);
        return new Result(true, StatusCode.OK,"删除成功");
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
        return new Result(true, StatusCode.OK,"查询成功",userService.findUserById(id));
    }

    /**
     * 条件查询
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功 ",userService.findSearch(searchMap));
    }

    /**
     * 条件+分页
     * @param searchMap
     * @param page
     * @param size
     */
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size){
        return new Result(true,StatusCode.OK,"查询成功",userService.findSearch(searchMap,page,size));
    }

    /**
     * 通过Xml查询成功
     * @return
     */
    @RequestMapping(value = "/findByXml",method = RequestMethod.GET)
    public Result findByXml(){
        return new Result(true,StatusCode.OK,"XML查询成功",userService.findByXml());
    }
}
