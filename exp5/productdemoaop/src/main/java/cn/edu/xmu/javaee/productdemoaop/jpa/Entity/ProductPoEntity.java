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
@Table(name = "goods_product")
public class ProductPoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long shopId;

    private Long goodsId;

    private Long categoryId;

    private Long templateId;

    private String skuSn;

    private String name;

    private Long originalPrice;

    private Long weight;

    private String barcode;

    private String unit;

    private String originPlace;

    private Long creatorId;

    private String creatorName;

    private Long modifierId;

    private String modifierName;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private Byte status;

    private Integer commissionRatio;

    private Long shopLogisticId;

    private Long freeThreshold;
}