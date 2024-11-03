//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.javaee.productdemoaop.mapper.join;

import cn.edu.xmu.javaee.productdemoaop.mapper.generator.ProductPoSqlProvider;
import cn.edu.xmu.javaee.productdemoaop.mapper.generator.po.OnSalePo;
import cn.edu.xmu.javaee.productdemoaop.mapper.generator.po.ProductPo;
import cn.edu.xmu.javaee.productdemoaop.mapper.generator.po.ProductPoExample;
import cn.edu.xmu.javaee.productdemoaop.mapper.join.po.ProductAllWithJoinPo;
import cn.edu.xmu.javaee.productdemoaop.mapper.manual.po.ProductAllPo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface ProductAllWithJoinMapper {

    @Select({
            "SELECT",
            "gp.id, gp.sku_sn, gp.name, gp.original_price, gp.weight, gp.barcode, gp.unit, gp.origin_place, ",
            "gp.commission_ratio, gp.free_threshold, gp.status, gp.creator_id, gp.creator_name, ",
            "gp.modifier_id, gp.modifier_name, gp.gmt_create, gp.gmt_modified, ",

            "go.id AS onsale_id, go.product_id, go.price, go.begin_time, go.end_time, go.quantity, ",
            "go.max_quantity, go.creator_id AS onsale_creator_id, go.creator_name AS onsale_creator_name, ",
            "go.modifier_id AS onsale_modifier_id, go.modifier_name AS onsale_modifier_name, go.gmt_create AS onsale_gmt_create, ",
            "go.gmt_modified AS onsale_gmt_modified, ",

            "gp2.id AS other_id, gp2.goods_id, gp2.sku_sn AS other_sku_sn, gp2.name AS other_name, ",
            "gp2.original_price AS other_original_price, gp2.weight AS other_weight, gp2.barcode AS other_barcode, ",
            "gp2.unit AS other_unit, gp2.origin_place AS other_origin_place, gp2.creator_id AS other_creator_id, ",
            "gp2.creator_name AS other_creator_name, gp2.modifier_id AS other_modifier_id, gp2.modifier_name AS other_modifier_name, ",
            "gp2.gmt_create AS other_gmt_create, gp2.gmt_modified AS other_gmt_modified ",

            "FROM goods_product gp ",
            "JOIN goods_onsale go ON gp.id = go.product_id AND go.begin_time <= NOW() AND go.end_time > NOW() ",
            "JOIN goods_product gp2 ON gp.goods_id = gp2.goods_id ",
            "WHERE gp.name = #{name, jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="sku_sn", property="skuSn", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="original_price", property="originalPrice", jdbcType=JdbcType.BIGINT),
            @Result(column="weight", property="weight", jdbcType=JdbcType.BIGINT),
            @Result(column="barcode", property="barcode", jdbcType=JdbcType.VARCHAR),
            @Result(column="unit", property="unit", jdbcType=JdbcType.VARCHAR),
            @Result(column="origin_place", property="originPlace", jdbcType=JdbcType.VARCHAR),
            @Result(column="commission_ratio", property="commissionRatio", jdbcType=JdbcType.INTEGER),
            @Result(column="free_threshold", property="freeThreshold", jdbcType=JdbcType.BIGINT),
            @Result(column="status", property="status", jdbcType=JdbcType.SMALLINT),
            @Result(column="creator_id", property="creatorId", jdbcType=JdbcType.BIGINT),
            @Result(column="creator_name", property="creatorName", jdbcType=JdbcType.VARCHAR),
            @Result(column="modifier_id", property="modifierId", jdbcType=JdbcType.BIGINT),
            @Result(column="modifier_name", property="modifierName", jdbcType=JdbcType.VARCHAR),
            @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP),
            @Result(property="onSaleList", javaType=List.class, many=@Many(resultMap = "onSaleResultMap",columnPrefix = "onsale_")),
            @Result(property="otherProduct", javaType=List.class, many=@Many(resultMap = "otherProductResultMap",columnPrefix = "other_"))
    })
    List<ProductAllWithJoinPo> getProductWithJoinByName(String name);

    @Select({
            "SELECT",
            "gp.id, gp.sku_sn, gp.name, gp.original_price, gp.weight, gp.barcode, gp.unit, gp.origin_place, ",
            "gp.commission_ratio, gp.free_threshold, gp.status, gp.creator_id, gp.creator_name, ",
            "gp.modifier_id, gp.modifier_name, gp.gmt_create, gp.gmt_modified, ",

            "go.id AS onsale_id, go.product_id, go.price, go.begin_time, go.end_time, go.quantity, ",
            "go.max_quantity, go.creator_id AS onsale_creator_id, go.creator_name AS onsale_creator_name, ",
            "go.modifier_id AS onsale_modifier_id, go.modifier_name AS onsale_modifier_name, go.gmt_create AS onsale_gmt_create, ",
            "go.gmt_modified AS onsale_gmt_modified, ",

            "gp2.id AS other_id, gp2.goods_id, gp2.sku_sn AS other_sku_sn, gp2.name AS other_name, ",
            "gp2.original_price AS other_original_price, gp2.weight AS other_weight, gp2.barcode AS other_barcode, ",
            "gp2.unit AS other_unit, gp2.origin_place AS other_origin_place, gp2.creator_id AS other_creator_id, ",
            "gp2.creator_name AS other_creator_name, gp2.modifier_id AS other_modifier_id, gp2.modifier_name AS other_modifier_name, ",
            "gp2.gmt_create AS other_gmt_create, gp2.gmt_modified AS other_gmt_modified ",

            "FROM goods_product gp ",
            "JOIN goods_onsale go ON gp.id = go.product_id AND go.begin_time <= NOW() AND go.end_time > NOW() ",
            "JOIN goods_product gp2 ON gp.goods_id = gp2.goods_id ",
            "WHERE gp.id = #{id, jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="sku_sn", property="skuSn", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="original_price", property="originalPrice", jdbcType=JdbcType.BIGINT),
            @Result(column="weight", property="weight", jdbcType=JdbcType.BIGINT),
            @Result(column="barcode", property="barcode", jdbcType=JdbcType.VARCHAR),
            @Result(column="unit", property="unit", jdbcType=JdbcType.VARCHAR),
            @Result(column="origin_place", property="originPlace", jdbcType=JdbcType.VARCHAR),
            @Result(column="commission_ratio", property="commissionRatio", jdbcType=JdbcType.INTEGER),
            @Result(column="free_threshold", property="freeThreshold", jdbcType=JdbcType.BIGINT),
            @Result(column="status", property="status", jdbcType=JdbcType.SMALLINT),
            @Result(column="creator_id", property="creatorId", jdbcType=JdbcType.BIGINT),
            @Result(column="creator_name", property="creatorName", jdbcType=JdbcType.VARCHAR),
            @Result(column="modifier_id", property="modifierId", jdbcType=JdbcType.BIGINT),
            @Result(column="modifier_name", property="modifierName", jdbcType=JdbcType.VARCHAR),
            @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP),
            @Result(property="onSaleList", javaType=List.class, many=@Many(resultMap = "onSaleResultMap",columnPrefix = "onsale_")),
            @Result(property="otherProduct", javaType=List.class, many=@Many(resultMap = "otherProductResultMap",columnPrefix = "other_"))
    })
    List<ProductAllWithJoinPo> getProductWithJoinById(Long id);

    @Select({
            "select",
            "`id`, `product_id`, `price`, `begin_time`, `end_time`, `quantity`, `max_quantity`, `creator_id`, ",
            "`creator_name`, `modifier_id`, `modifier_name`, `gmt_create`, `gmt_modified`",
            "from goods_onsale",
            "where `product_id` = #{productId,jdbcType=BIGINT} and `begin_time` <= NOW() and `end_time` > NOW()"
    })
    @Results(id = "onSaleResultMap", value = {
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
            @Result(column="price", property="price", jdbcType=JdbcType.BIGINT),
            @Result(column="begin_time", property="beginTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="end_time", property="endTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="quantity", property="quantity", jdbcType=JdbcType.INTEGER),
            @Result(column="max_quantity", property="maxQuantity", jdbcType=JdbcType.INTEGER),
            @Result(column="creator_id", property="creatorId", jdbcType=JdbcType.BIGINT),
            @Result(column="creator_name", property="creatorName", jdbcType=JdbcType.VARCHAR),
            @Result(column="modifier_id", property="modifierId", jdbcType=JdbcType.BIGINT),
            @Result(column="modifier_name", property="modifierName", jdbcType=JdbcType.VARCHAR),
            @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP)
    })
    List<OnSalePo> selectLastOnSaleByProductId(Long productId);

    @Select({
            "select",
            "`id`, `goods_id`, `sku_sn`, `name`, `original_price`, `weight`, ",
            "`barcode`, `unit`, `origin_place`, `creator_id`, `creator_name`, `modifier_id`, ",
            "`modifier_name`, `gmt_create`, `gmt_modified`",
            "from goods_product",
            "where `goods_id` = #{goodsId,jdbcType=BIGINT}"
    })

    @Results(id = "otherProductResultMap", value = {
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="sku_sn", property="skuSn", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="original_price", property="originalPrice", jdbcType=JdbcType.BIGINT),
            @Result(column="weight", property="weight", jdbcType=JdbcType.BIGINT),
            @Result(column="barcode", property="barcode", jdbcType=JdbcType.VARCHAR),
            @Result(column="unit", property="unit", jdbcType=JdbcType.VARCHAR),
            @Result(column="origin_place", property="originPlace", jdbcType=JdbcType.VARCHAR),
            @Result(column="creator_id", property="creatorId", jdbcType=JdbcType.BIGINT),
            @Result(column="creator_name", property="creatorName", jdbcType=JdbcType.VARCHAR),
            @Result(column="modifier_id", property="modifierId", jdbcType=JdbcType.BIGINT),
            @Result(column="modifier_name", property="modifierName", jdbcType=JdbcType.VARCHAR),
            @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP)
    })
    ProductPo selectOtherProduct(Long goodsId);

}