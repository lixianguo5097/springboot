package com.lxg.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


/**
 * 用户实体类
 * @author LXG
 * @date 2019-10-28
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
