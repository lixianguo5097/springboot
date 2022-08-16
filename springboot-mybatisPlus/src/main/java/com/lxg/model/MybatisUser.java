package com.lxg.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * 用户实体类
 * @author LXG
 * @date 2019-10-28
 */
@TableName("t_user")
@Data
public class MybatisUser implements Serializable {
    /** id */
    @NotBlank(message = "id不能为空")
    private String id;
    /** 姓名 */
    @NotBlank(message = "姓名不能为空")
    private String name;
    /** 年龄 */
    @NotNull(message = "年龄不能为空")
    private Integer age;
}
