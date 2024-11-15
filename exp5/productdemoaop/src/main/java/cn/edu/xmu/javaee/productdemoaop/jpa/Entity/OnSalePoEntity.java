package cn.edu.xmu.javaee.productdemoaop.jpa.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "goods_onsale")
public class OnSalePoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long shopId;

    private Long productId;

    private Long price;

    private LocalDateTime beginTime;

    private LocalDateTime endTime;

    private Integer quantity;

    private Byte type;

    private Long creatorId;

    private String creatorName;

    private Long modifierId;

    private String modifierName;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private Integer maxQuantity;

    private Byte invalid;
}