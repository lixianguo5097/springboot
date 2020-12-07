package com.lxg.user.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lxg.common.base.BaseModel;
import com.lxg.common.base.BaseResult;
import com.lxg.common.exception.MyException;
import com.lxg.common.utils.MD5Utils;
import com.lxg.common.utils.RedisCacheUtils;
import com.lxg.user.mapper.SysUserMapper;
import com.lxg.user.model.SysUser;
import com.lxg.user.model.SysUserRole;
import com.lxg.user.req.DelUserReq;
import com.lxg.user.req.SaveUserReq;
import com.lxg.user.req.SelectUserPageReq;
import com.lxg.user.req.UpdatePwdReq;
import com.lxg.user.resp.SelectUserPageResp;
import com.lxg.user.resp.SelectUserResp;
import com.lxg.user.service.SysUserRoleService;
import com.lxg.user.service.SysUserService;
import com.lxg.user.utils.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author PH
 * @since 2020-04-08
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    /**
     * 初始密码
     */
    private static final String DEFAULT_PASSWORD = "888888";

    @Autowired
    private RedisCacheUtils redisCacheUtils;
    @Autowired
    private SysUserRoleService userRoleService;

    /**
     * 通过用户姓名查找用户
     * @param username 用户名字
     * @return User
     */
    @Override
    public SysUser findUserByName(String username) {
        SysUser condition = new SysUser();
        condition.setUsername(username);
        return selectOne(new EntityWrapper<>(condition));
    }

    /**
     * @ClassName UserServiceImpl
     * @Author ph
     * @Version 1.0
     * @Description
     * @Date 2020/3/9 15:56
     */
    @Override
    public void logout() {
        //删除sessionId
        String sessionId = (String) SecurityUtils.getSubject().getSession().getId();
        redisCacheUtils.delete("shiro:session:" + sessionId);
        log.info("退出登录，删除redis成功");
    }
    /**
     * 根据条件分页查询用户信息
     *
     * @param req
     * @return
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public BaseResult findUserByPage(SelectUserPageReq req) {
        EntityWrapper<SysUser> wrapper = new EntityWrapper<>(new SysUser());
        if (StringUtils.isNotBlank(req.getSearchContent())) {
            wrapper.like("username", req.getSearchContent()).or().like("real_name", req.getSearchContent());
        }
        if (req.getRoleId() != null) {
            List<String> userIdList = userRoleService.selectUserIdListByRole(req.getRoleId());
            wrapper.in("id", userIdList);
        }
        wrapper.orderBy(BaseModel.CREATE_TIME, false);
        SelectUserPageResp pageResp = new SelectUserPageResp();
        Page<SysUser> page = selectPage(req.getPage(), wrapper);
        BeanUtils.copyProperties(page, pageResp);
        List<SysUser> userList = page.getRecords();
        List<SelectUserResp> respList = new ArrayList<>(userList.size());
        if (!CollectionUtils.isEmpty(userList)) {
            for (SysUser user : userList) {
                SelectUserResp userResp = new SelectUserResp();
                BeanUtils.copyProperties(user, userResp);
                //查询对应角色
                SysUserRole userRole = new SysUserRole();
                userRole.setUserId(user.getId());
                SysUserRole userRoleInfo = userRoleService.selectOne(new EntityWrapper<>(userRole));
                if (userRoleInfo != null) {
                    userResp.setRole(userRoleInfo.getRoleId());
                }
                respList.add(userResp);
            }
            pageResp.setRecords(respList);
        }
        return BaseResult.success(pageResp);
    }

    /**
     * 新增或者修改用户信息
     *
     * @param req
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult saveOrUpdateUserInfo(SaveUserReq req) {
        SysUser sysUser = new SysUser();
        SysUserRole userRole = new SysUserRole();
        if (req == null) {
            log.info("新增或修改用户至少传入一个参数");
            throw MyException.newException("至少传入一个参数");
        }

        if (StringUtils.isNotBlank(req.getId())) {
            //修改操作
            //验证用户名是否存在
            if (StringUtils.isNotBlank(req.getUsername())) {
                userNameExistOrNotForUpdate(req.getUsername(), req.getId());
            }
            sysUser.setId(req.getId());
            //找到user-role表，并修改roleId信息
            SysUserRole condition = new SysUserRole();
            condition.setUserId(req.getId());
            SysUserRole selectOne = userRoleService.selectOne(new EntityWrapper<>(condition));
            if (selectOne != null) {
                userRole.setId(selectOne.getId());
            }
        } else {
            //新增操作
            //验证用户名是否存在
            if (StringUtils.isNotBlank(req.getUsername())) {
                userNameExistOrNot(req.getUsername());
            }
            //设置初始密码
            String defaultPwd = createPwd();
            sysUser.setPassword(defaultPwd);
        }
        BeanUtils.copyProperties(req, sysUser);
        boolean b = insertOrUpdate(sysUser);
        userRole.setRoleId(req.getRoleType().toString());
        userRole.setUserId(sysUser.getId());
        userRoleService.insertOrUpdate(userRole);
        if (b) {
            return BaseResult.success("操作成功");
        } else {
            return BaseResult.error("操作失败");
        }
    }

    /**
     * 初始密码
     *
     * @return
     */
    public String createPwd() {
        //原始密码
        String sourceMd5 = MD5Utils.md5(DEFAULT_PASSWORD);
        //散列次数
        int hashIterations = 2;
        /*	方法1：
         * 第一个参数：明文，原始密码
         * 第二个参数：盐，通常使用随机数，这里指定固定字符串
         * 第三个参数：散列的次数，比如散列两次，相当 于md5(md5(''))
         */
        Md5Hash md5Hash = new Md5Hash(sourceMd5, null, hashIterations);
        return md5Hash.toString();
    }

    public static void main(String[] args) {
        //散列次数
        int hashIterations = 2;
        /*	方法1：
         * 第一个参数：明文，原始密码
         * 第二个参数：盐，通常使用随机数，这里指定固定字符串
         * 第三个参数：散列的次数，比如散列两次，相当 于md5(md5(''))
         */
        Md5Hash md5Hash = new Md5Hash("lxg", null, hashIterations);
        System.out.println(md5Hash.toString());
    }

    /**
     * 判断用户名是否存在
     *
     * @param username
     */
    public void userNameExistOrNot(String username) {
        SysUser user = new SysUser();
        user.setUsername(username);
        SysUser selectOne = selectOne(new EntityWrapper<>(user));
        if (selectOne != null) {
            log.info("已经存在用户名名为：" + username);
            throw MyException.newException("已经存在用户名为：" + username);
        }
    }

    /**
     * 判断用户名是否存在（修改时判断）
     *
     * @param userName
     * @param userId
     */
    public void userNameExistOrNotForUpdate(String userName, String userId) {
        SysUser user = new SysUser();
        List<SysUser> userList = selectList(new EntityWrapper<>(user));
        if (!CollectionUtils.isEmpty(userList)) {
            //有一条或者多条
            if (userList.size() == 1) {
                //判断是否是本身
                SysUser sysUser = userList.get(0);
                if (!userId.equals(sysUser.getId())) {
                    //已存在
                    log.info("已经存在用户名名为：" + userName);
                    throw MyException.newException("已经存在用户名为：" + userName);
                }

            } else {
                //多条证明肯定存在
                log.info("已经存在用户名名为：" + userName);
                throw MyException.newException("已经存在用户名为：" + userName);
            }
        }

    }

    /**
     * 重置密码
     *
     * @param req
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult resetPassword(DelUserReq req) {
        SysUser sysUser = new SysUser();
        sysUser.setId(req.getId());
        sysUser.setPassword(createPwd());
        boolean b = updateById(sysUser);
        if (b) {
            return BaseResult.success("操作成功");
        } else {
            return BaseResult.error("操作失败");
        }
    }

    /**
     * 删除用户
     *
     * @param req
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult delUser(DelUserReq req) {
        boolean b = deleteById(req.getId());
        if (b) {
            //将user-role表也删除
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(req.getId());
            SysUserRole selectOne = userRoleService.selectOne(new EntityWrapper<>(sysUserRole));
            if (selectOne != null) {
                userRoleService.deleteById(selectOne.getId());
            }
            return BaseResult.success("操作成功");
        } else {
            return BaseResult.error("操作失败");
        }
    }

    /**
     * 根据用户id查询信息
     *
     * @param req
     * @return
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public SelectUserResp selectUserById(DelUserReq req) {
        SysUser user = selectById(req.getId());
        SelectUserResp resp = new SelectUserResp();
        if (user == null) {
            log.info("用户不存在，id为：" + req.getId());
            throw MyException.newException("该用户不存在");
        }
        resp.setId(user.getId());
        SysUserRole condition = new SysUserRole();
        condition.setUserId(user.getId());
        SysUserRole userRoleInfo = userRoleService.selectOne(new EntityWrapper<>(condition));
        if (userRoleInfo != null) {
            resp.setRole(userRoleInfo.getRoleId());
        }
        resp.setUsername(user.getUsername());
        return resp;
    }

    /**
     * 修改用户密码
     *
     * @param req
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult updatePassword(UpdatePwdReq req) {
        String userId = ShiroUtils.getUserId();
        if (StringUtils.isBlank(userId)) {
            log.info("当前用户不存在");
            throw MyException.newException("当前用户不存在");
        }
        Md5Hash oldPassword = new Md5Hash(req.getOldPassword(), null, 2);
        String oldPasswordStr = oldPassword.toString();
        SysUser userInfo = selectById(userId);
        if (userInfo == null) {
            log.info("id为：" + userId + "的用户不存在");
            throw MyException.newException("该用户不存在");
        }
        if (!oldPasswordStr.equals(userInfo.getPassword())) {
            log.info("旧密码输入错误，用户ID为：" + userId);
            throw MyException.newException("旧密码输入错误");
        }
        Md5Hash newPassword = new Md5Hash(req.getNewPassword(), null, 2);
        userInfo.setPassword(newPassword.toString());
        boolean b = updateById(userInfo);
        if (b) {
            return BaseResult.success("操作成功");
        } else {
            return BaseResult.error("操作失败");
        }
    }
}
