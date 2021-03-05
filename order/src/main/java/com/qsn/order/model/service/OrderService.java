package com.qsn.order.model.service;


import com.qsn.order.model.entity.Order;

import java.util.List;

/**
 * 服务接口
 *
 * @author qiusn
 * @date 2021-03-05
 */
public interface OrderService {


    /**
     * 列表
     *
     * @param order 查询条件
     * @return 列表信息
     */
    List<Order> listOrder(Order order);

    /**
     * 新增
     *
     * @param order 新增信息
     * @return 成功或失败
     */
    boolean insertOrder(Order order);

    /**
     * 修改
     *
     * @param order 根据主键修改信息
     * @return 成功或失败
     */
    boolean updateOrderById(Order order);

    /**
     * 详情
     *
     * @param order 根据主键获取详情
     * @return 详情
     */
    Order getOrderById(Order order);

}
