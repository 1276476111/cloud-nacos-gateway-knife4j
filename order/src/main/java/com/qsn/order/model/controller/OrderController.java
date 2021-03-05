package com.qsn.order.model.controller;


import com.qsn.order.model.entity.Order;
import com.qsn.order.model.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 前端控制器
 *
 * @author qiusn
 * @date 2021-03-05
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class OrderController {

    @Resource
    private OrderService orderService;


    /**
     * 列表
     *
     * @param order 查询条件
     * @return 列表信息
     */
//    @ApiOperation(value = "列表", notes = "备注信息", tags = "")
    @PostMapping("/order/list")
    public List<Order> listOrder(@RequestBody Order order) {
        List<Order> list = orderService.listOrder(order);
        return list;
    }

    /**
     * 新增
     *
     * @param order 新增信息
     * @return 成功或失败
     */
//    @ApiOperation(value = "新增", notes = "新增一条信息", tags = "")
    @PostMapping("/order/insert")
    public String insertOrder(@RequestBody Order order) {
        orderService.insertOrder(order);
        return "ok";
    }

    /**
     * 修改
     *
     * @param order 根据主键修改信息
     * @return 成功或失败
     */
//    @ApiOperation(value = "修改", notes = "根据 ID 修改一条信息", tags = "")
    @PostMapping("/order/updateById")
    public String updateOrderById(@RequestBody Order order) {
        orderService.updateOrderById(order);
        return "ok";
    }

    /**
     * 详情
     *
     * @param order 根据主键获取详情
     * @return 详情
     */
//    @ApiOperation(value = "详情", notes = "根据 ID 获取一条信息", tags = "")
    @PostMapping("/order/getById")
    public Order getOrderById(@RequestBody Order order) {
        Order detail = orderService.getOrderById(order);
        return detail;
    }


}
