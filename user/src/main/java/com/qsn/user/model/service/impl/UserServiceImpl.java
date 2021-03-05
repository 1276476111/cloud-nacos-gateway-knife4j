package com.qsn.user.model.service.impl;

import com.qsn.user.model.entity.User;
import com.qsn.user.model.mapper.UserMapper;
import com.qsn.user.model.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 用户表用于测试 服务实现类
 *
 * @author qiusn
 * @date 2021-03-05
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;


    @Override
    public List<User> listUser(User user){
        return userMapper.listUser(user);
    }

    @Override
    public boolean insertUser(User user){
        int insertNum = userMapper.insertUser(user);
        return insertNum == 1 ? true : false;
    }

    @Override
    public boolean updateUserById(User user){
        int updateNum = userMapper.updateUserById(user);
        return updateNum == 1 ? true : false;
    }

    @Override
    public User getUserById(User user){
        return userMapper.getUserById(user);
    }


}
