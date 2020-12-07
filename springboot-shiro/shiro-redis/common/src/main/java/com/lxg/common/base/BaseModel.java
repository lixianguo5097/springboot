package com.lxg.common.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 *  基础model
 * @Author: PH
 * @Date: 2019/4/15 17:07
 */
@Data
public class BaseModel {

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String CREATE_BY = "create_by";

    public static final String UPDATE_BY = "update_by";

    public static final String DEL_FLAG = "del_flag";

    public static final String DATE_TIME = "date_time";

    public static final String SORT = "sort";

    /**
     * 创建时间
     */
    @TableField(value="create_time",fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(value="update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 创建人
     */
    @TableField(value="create_by",fill = FieldFill.INSERT)
    @JsonIgnore
    private String createBy;
    /**
     * 更新人
     */
    @TableField(value="update_by",fill = FieldFill.INSERT_UPDATE)
    @JsonIgnore
    private String updateBy;


    /**
     * 逻辑删除标志
     */
    @TableField(value="del_flag",fill = FieldFill.INSERT)
    @TableLogic
    @JsonIgnore
    private Boolean delFlag;
}
