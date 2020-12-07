package com.lxg.user.resp;

import com.lxg.common.base.BasePageResp;
import lombok.Data;

import java.util.List;

/**
 * @ClassName SelectUserPageResp
 * @Description
 * @Author ph
 * @Date 2020/3/18 11:04
 * @Version 1.0
 **/
@Data
public class SelectUserPageResp extends BasePageResp {

    List<SelectUserResp> records;
}
