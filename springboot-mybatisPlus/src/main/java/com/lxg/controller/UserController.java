package com.lxg.controller;


import com.lxg.common.Result;
import com.lxg.model.MybatisUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lxg.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * 用户controller
 * @author LXG
 * @date 2019-10-31
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 新增
     * @param mybatisUser
     */
    @PostMapping
    public Result addUser(@RequestBody MybatisUser mybatisUser) {
        userService.addUser(mybatisUser);
        return Result.success();
    }

    /**
     * 根据id修改
     * @param mybatisUser
     */
    @PutMapping
    public Result updateUser(@RequestBody MybatisUser mybatisUser) {
        if (mybatisUser.getId() == null || mybatisUser.getId().equals("")) {
            return Result.fail("无id，更新失败");
        }
        userService.updateUser(mybatisUser);
        return Result.success();
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        userService.deleteUser(id);
        return Result.success();
    }

    /**
     * 查询所有
     */
    @GetMapping
    public List<MybatisUser> findAllUser() {
        return userService.findAll();
    }

    /**
     * 根据id查询
     * @param id
     */
    @GetMapping(value = "/{id}")
    public Result findByUserId(@PathVariable String id) {
        return Result.success(userService.findUserById(id));
    }

    /**
     * 条件查询
     */
    @PostMapping(value="/search")
    public Result findSearch(@RequestBody Map searchMap){
        return Result.success(userService.findSearch(searchMap));
    }

    /**
     * 条件+分页
     * @param searchMap
     * @param page
     * @param size
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size){
        return Result.success(userService.findSearch(searchMap,page,size));
    }

    /**
     * 通过Xml查询成功
     * @return
     */
    @GetMapping(value = "/findByXml")
    public Result findByXml(){
        return Result.success(userService.findByXml());
    }
}
