package com.lxg.controller;


import com.lxg.entity.Result;
import com.lxg.entity.Student;
import com.lxg.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 保存/新增
     */
    @PostMapping("/save")
    public Result save(@RequestBody Student student) {
        studentService.saveOrUpdate(student);
        return Result.success();
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping(value = "/del/{id}")
    public Result delete(@PathVariable String id) {
        studentService.removeById(id);
        return Result.success();
    }


    /**
     * 查询所有
     */
    @GetMapping
    public List<Student> findAll() {
        return studentService.list();
    }
}
