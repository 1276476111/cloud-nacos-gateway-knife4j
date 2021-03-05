package com.qsn.user.model.service;

import com.qsn.user.model.entity.User;

import java.util.List;

/**
 * 用户表用于测试 服务接口
 *
 * @author qiusn
 * @date 2021-03-05
 */
public interface UserService {


    /**
     * 用户表用于测试列表
     *
     * @param user 查询条件
     * @return 列表信息
     */
    List<User> listUser(User user);

    /**
     * 用户表用于测试新增
     *
     * @param user 新增信息
     * @return 成功或失败
     */
    boolean insertUser(User user);

    /**
     * 用户表用于测试修改
     *
     * @param user 根据主键修改信息
     * @return 成功或失败
     */
    boolean updateUserById(User user);

    /**
     * 用户表用于测试详情
     *
     * @param user 根据主键获取详情
     * @return 用户表用于测试详情
     */
    User getUserById(User user);

}
