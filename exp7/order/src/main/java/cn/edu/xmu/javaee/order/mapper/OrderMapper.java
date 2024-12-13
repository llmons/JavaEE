package cn.edu.xmu.javaee.order.mapper;

import cn.edu.xmu.javaee.order.dao.bo.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface OrderMapper extends MongoRepository<Order, String>{

    Order findFirstByOrderSn(String orderSn);
}
