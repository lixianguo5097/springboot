package com.lxg.user.service;

import com.baomidou.mybatisplus.service.IService;
import com.lxg.common.base.BaseResult;
import com.lxg.user.model.SysUser;
import com.lxg.user.req.DelUserReq;
import com.lxg.user.req.SaveUserReq;
import com.lxg.user.req.SelectUserPageReq;
import com.lxg.user.req.UpdatePwdReq;
import com.lxg.user.resp.SelectUserResp;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author PH
 * @since 2020-04-08
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 通过用户姓名查找用户
     *
     * @param username 用户名字
     * @return User
     */
    SysUser findUserByName(String username);

    /**
     * 退出登录
     *
     */
    void logout();

    /**
     * 根据条件分页查询用户信息
     *
     * @param req
     * @return
     */
    BaseResult findUserByPage(SelectUserPageReq req);

    /**
     * 新增或者修改用户信息
     *
     * @param req
     * @return
     */
    BaseResult saveOrUpdateUserInfo(SaveUserReq req);

    /**
     * 重置密码
     *
     * @param req
     * @return
     */
    BaseResult resetPassword(DelUserReq req);

    /**
     * 删除用户
     *
     * @param req
     * @return
     */
    BaseResult delUser(DelUserReq req);

    /**
     * 根据用户id查询信息
     *
     * @param req
     * @return
     */
    SelectUserResp selectUserById(DelUserReq req);

    /**
     * 修改用户密码
     *
     * @param req
     * @return
     */
    BaseResult updatePassword(UpdatePwdReq req);
}
