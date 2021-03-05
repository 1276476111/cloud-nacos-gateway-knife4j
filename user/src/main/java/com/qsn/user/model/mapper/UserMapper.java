package com.qsn.user.model.mapper;

import com.qsn.user.model.entity.User;

import java.util.List;

/**
 * Mapper 接口
 *
 * @author qiusn
 * @date 2021-03-05
 */
public interface UserMapper {

    /**
     * 用户表用于测试分页
     *
     * @param user 查询条件
     * @return 分页信息
     */
    List<User> listPageUser(User user);

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
     * @return 影响记录行数
     */
    int insertUser(User user);

    /**
     * 用户表用于测试修改
     *
     * @param user 主键、待修改信息
     * @return 影响记录行数
     */
    int updateUserById(User user);


    /**
     * 用户表用于测试详情
     *
     * @param user 根据主键获取详情
     * @return 用户表用于测试详情
     */
    User getUserById(User user);

}
