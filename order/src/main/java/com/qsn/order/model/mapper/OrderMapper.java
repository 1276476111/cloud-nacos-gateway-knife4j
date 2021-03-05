package com.qsn.order.model.mapper;


import com.qsn.order.model.entity.Order;

import java.util.List;

/**
 * Mapper 接口
 *
 * @author qiusn
 * @date 2021-03-05
 */
public interface OrderMapper {

    /**
     * 分页
     *
     * @param order 查询条件
     * @return 分页信息
     */
    List<Order> listPageOrder(Order order);

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
     * @return 影响记录行数
     */
    int insertOrder(Order order);

    /**
     * 修改
     *
     * @param order 主键、待修改信息
     * @return 影响记录行数
     */
    int updateOrderById(Order order);


    /**
     * 详情
     *
     * @param order 根据主键获取详情
     * @return 详情
     */
    Order getOrderById(Order order);

}
