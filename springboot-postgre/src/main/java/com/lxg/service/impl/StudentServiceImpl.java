package com.lxg.service.impl;

import com.lxg.entity.Student;
import com.lxg.mapper.StudentMapper;
import com.lxg.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lxg
 * @since 2022-08-12
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
