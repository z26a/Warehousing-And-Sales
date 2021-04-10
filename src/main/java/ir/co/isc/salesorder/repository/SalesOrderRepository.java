package ir.co.isc.salesorder.repository;

import ir.co.isc.salesorder.model.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {
    @Query("select salesOrder from SalesOrder salesOrder where salesOrder.customerId=:id ")
    List findOrdersByCustomerId(Long id);

    @Modifying
    @Query("update SalesOrder salesOrder set salesOrder.orderActive = 'INACTIVE' where salesOrder.id = :id")
    void deactivateOrderById(@Param("id") Long id);

}
