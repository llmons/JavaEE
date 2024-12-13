//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.javaee.order.controller.vo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class OrderItemVo {

    @NotBlank(message = "商品销售不能为空")
    private Long onsaleId;

    @Min(value = 1, message = "至少购买一个")
    private Integer quantity;

    private Long actId;

    private Long couponId;
}

