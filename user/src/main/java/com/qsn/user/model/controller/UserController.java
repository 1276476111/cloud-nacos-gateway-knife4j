package com.qsn.user.model.controller;


import com.qsn.user.model.entity.User;
import com.qsn.user.model.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户表用于测试 前端控制器
 *
 * @author qiusn
 * @date 2021-03-05
 */
@Api(tags = "用户管理", value = "UserController")
@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    @Resource
    private UserService userService;


    /**
     * 用户表用于测试列表
     *
     * @param user 查询条件
     * @return 列表信息
     */
    @ApiOperation(value = "用户表用于测试列表", notes = "备注信息")
    @PostMapping("/user/list")
    public List<User> listUser(@RequestBody User user) {
        List<User> list = userService.listUser(user);
        return list;
    }

    /**
     * 用户表用于测试新增
     *
     * @param user 新增信息
     * @return 成功或失败
     */
    @ApiOperation(value = "用户表用于测试新增", notes = "新增一条信息")
    @PostMapping("/user/insert")
    public String insertUser(@RequestBody User user) {
        userService.insertUser(user);
        return "ok";
    }

    /**
     * 用户表用于测试修改
     *
     * @param user 根据主键修改信息
     * @return 成功或失败
     */
    @ApiOperation(value = "用户表用于测试修改", notes = "根据 ID 修改一条信息")
    @PostMapping("/user/updateById")
    public String updateUserById(@RequestBody User user) {
        userService.updateUserById(user);
        return "ok";
    }

    /**
     * 用户表用于测试详情
     *
     * @param user 根据主键获取详情
     * @return 用户表用于测试详情
     */
    @ApiOperation(value = "用户表用于测试详情", notes = "根据 ID 获取一条信息")
    @PostMapping("/user/getById")
    public User getUserById(@RequestBody User user) {
        User detail = userService.getUserById(user);
        return detail;
    }


}
