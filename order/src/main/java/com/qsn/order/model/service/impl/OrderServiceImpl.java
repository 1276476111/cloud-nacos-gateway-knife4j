package com.qsn.order.model.service.impl;

import com.qsn.order.model.entity.Order;
import com.qsn.order.model.mapper.OrderMapper;
import com.qsn.order.model.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 *  服务实现类
 *
 * @author qiusn
 * @date 2021-03-05
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;


    @Override
    public List<Order> listOrder(Order order){
        return orderMapper.listOrder(order);
    }

    @Override
    public boolean insertOrder(Order order){
        int insertNum = orderMapper.insertOrder(order);
        return insertNum == 1 ? true : false;
    }

    @Override
    public boolean updateOrderById(Order order){
        int updateNum = orderMapper.updateOrderById(order);
        return updateNum == 1 ? true : false;
    }

    @Override
    public Order getOrderById(Order order){
        return orderMapper.getOrderById(order);
    }


}
