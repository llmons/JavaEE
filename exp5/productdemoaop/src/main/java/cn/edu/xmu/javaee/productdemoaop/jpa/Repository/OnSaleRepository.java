package cn.edu.xmu.javaee.productdemoaop.jpa.Repository;

import cn.edu.xmu.javaee.productdemoaop.jpa.Entity.OnSalePoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OnSaleRepository extends JpaRepository<OnSalePoEntity, Long> {

    List<OnSalePoEntity> findByProductIdAndBeginTimeLessThanEqualAndEndTimeGreaterThanEqualOrderByEndTimeDesc(
            Long productId, LocalDateTime beginTime, LocalDateTime endTime);
}
