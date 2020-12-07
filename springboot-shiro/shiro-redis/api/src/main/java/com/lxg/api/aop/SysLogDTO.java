package com.lxg.api.aop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysLogDTO {

    /**
     * 操作模块
     * @return
     */
    String operateModule;

    /**
     * 操作内容
     * @return
     */
    String operation;
}
