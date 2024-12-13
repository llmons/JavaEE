package cn.edu.xmu.javaee.order.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto{

    private String id;

    private String orderSn;

    private String consignee;

    private String address;

    private String mobile;

    private String message;

    private List<OrderItemDto> orderItems;
}
