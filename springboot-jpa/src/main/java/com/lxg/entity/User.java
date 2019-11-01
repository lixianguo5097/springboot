package com.lxg.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @Description: 用户实体类
 * @Author: XIANGUO LI
 * @Date: 2019-10-28 17:33
 */

@Data
@Entity
@Table(name="t_user")
public class User implements Serializable {
    /** 编号 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    /** 姓名 */
    private String name;
    /** 年龄 */
    private Integer age;
}
