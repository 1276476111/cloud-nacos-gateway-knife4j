package com.qsn.order.model.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.qsn.order.model.entity.Order;
import com.qsn.order.model.service.OrderService;
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
 * 该类为了测试排序
 *
 * @author qiusn
 * @date 2021-03-05
 */
@Api(tags = "用户管理", value = "UserController")
@ApiSort(2)
@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    @Resource
    private OrderService orderService;


    /**
     * 用户表用于测试列表
     *
     * @param user 查询条件
     * @return 列表信息
     */
    @ApiOperation(value = "用户表用于测试列表", notes = "备注信息", position = 1)
    @PostMapping("/user/list")
    public List<Order> listUser(@RequestBody Order user) {
        List<Order> list = orderService.listOrder(user);
        return list;
    }

    /**
     * 用户表用于测试新增
     *
     * @param user 新增信息
     * @return 成功或失败
     */
    @ApiOperation(value = "用户表用于测试新增", notes = "新增一条信息", position = 3)
    @PostMapping("/user/insert")
    public String insertUser(@RequestBody Order user) {
        orderService.insertOrder(user);
        return "ok";
    }

    /**
     * 用户表用于测试修改
     *
     * @param user 根据主键修改信息
     * @return 成功或失败
     */
    @ApiOperation(value = "用户表用于测试修改", notes = "根据 ID 修改一条信息", position = 4)
    @PostMapping("/user/updateById")
    public String updateUserById(@RequestBody Order user) {
        orderService.updateOrderById(user);
        return "ok";
    }

    /**
     * 用户表用于测试详情
     *
     * @param user 根据主键获取详情
     * @return 用户表用于测试详情
     */
    @ApiOperation(value = "用户表用于测试详情", notes = "根据 ID 获取一条信息", position = 2)
    @PostMapping("/user/getById")
    public Order getUserById(@RequestBody Order user) {
        Order detail = orderService.getOrderById(user);
        return detail;
    }


}
