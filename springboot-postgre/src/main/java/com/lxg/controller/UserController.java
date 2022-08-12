package com.lxg.controller;


import com.lxg.entity.Result;
import com.lxg.entity.User;
import com.lxg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lxg
 * @since 2022-08-12
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 新增
     * @param user
     */
    @PostMapping
    public Result addUser(@Valid @RequestBody User user) {
        userService.save(user);
        return Result.success();
    }

    /**
     * 根据id修改
     * @param user
     */
    @PutMapping
    public Result updateUser(@RequestBody User user) {
        if (user.getId() == null) {
            return Result.fail("无id，更新失败");
        }
        userService.updateById(user);
        return Result.success();
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        userService.removeById(id);
        return Result.success();
    }

    /**
     * 查询所有
     */
    @GetMapping
    public List<User> findAllUser() {
        return userService.list();
    }
}
