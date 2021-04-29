package ir.co.isc.salesorder.repository;

import ir.co.isc.salesorder.model.OrderItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {

 void deleteAllBySalesOrderId(Long orderId);

}
