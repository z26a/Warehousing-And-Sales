package ir.co.isc.salesorder.repository;

import ir.co.isc.salesorder.model.SalesOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesOrderRepository extends CrudRepository<SalesOrder, Long> {
    @Query("select salesOrder from SalesOrder salesOrder where salesOrder.customerId=:id ")
    List findOrdersByCustomerId(Long id);
}
