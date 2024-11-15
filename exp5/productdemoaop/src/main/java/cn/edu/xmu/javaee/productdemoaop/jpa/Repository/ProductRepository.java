package cn.edu.xmu.javaee.productdemoaop.jpa.Repository;

import cn.edu.xmu.javaee.productdemoaop.jpa.Entity.ProductPoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductPoEntity, Long> {

    List<ProductPoEntity> findByName(String name);

    List<ProductPoEntity> findByGoodsIdAndIdNot(Long goodsId, Long id);
}
