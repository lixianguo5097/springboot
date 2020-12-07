package com.lxg.user.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lxg
 * @date 2020-2-25
 */
@Data
public class SysLog implements Serializable {

    private static final long serialVersionUID = -3916490113130386762L;

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 操作内容
     */
    private String operation;
    /**
     * 操作模块
     */
    @TableField("operation_module")
    private String operationModule;
    /**
     * 用户名
     */
    private String username;
    /**
     * 时间
     */
    @TableField("date_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date dateTime;

    /**
     * url
     */
    private String url;

    /**
     * 返回参数
     */
    private String respParam;
    /**
     * 是否错误
     */
    private Boolean errorLogFlag;
}
