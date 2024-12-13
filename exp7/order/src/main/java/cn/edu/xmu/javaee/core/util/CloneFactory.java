package cn.edu.xmu.javaee.core.util;

import cn.edu.xmu.javaee.order.controller.dto.OrderDto;
import cn.edu.xmu.javaee.order.controller.dto.OrderItemDto;
import cn.edu.xmu.javaee.order.controller.vo.OrderItemVo;
import cn.edu.xmu.javaee.order.controller.vo.OrderVo;
import cn.edu.xmu.javaee.order.dao.bo.Order;
import cn.edu.xmu.javaee.order.dao.bo.OrderItem;

public class CloneFactory {

    public static  Order copy(Order target, OrderVo source){
        return Order.builder()
                .consignee(source.getConsignee())
                .address(source.getAddress())
                .message(source.getMessage())
                .regionId(source.getRegionId())
                .mobile(source.getMobile())
                .build();
    }

    public static OrderItem copy(OrderItem target, OrderItemVo source){
        return OrderItem.builder()
                .actId(source.getActId())
                .couponId(source.getCouponId())
                .onsaleId(source.getOnsaleId())
                .quantity(source.getQuantity())
                .build();
    }

    public static OrderDto copy(OrderDto target, Order source){
        return OrderDto.builder()
                .id(source.getId())
                .address(source.getAddress())
                .mobile(source.getMobile())
                .orderSn(source.getOrderSn())
                .consignee(source.getConsignee())
                .build();
    }

    public static OrderItemDto copy(OrderItemDto target, OrderItem source){
        return OrderItemDto.builder()
                .id(source.getId())
                .onsaleId(source.getOnsaleId())
                .price(source.getPrice())
                .quantity(source.getQuantity())
                .name(source.getName())
                .point(source.getPoint())
                .discountPrice(source.getDiscountPrice())
                .build();
    }
}
