package ir.co.isc.salesorder.repository;

import ir.co.isc.salesorder.model.OrderItem;
import ir.co.isc.salesorder.model.SalesOrder;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface SalesOrderRepository extends CrudRepository<SalesOrder, Long> {

    @Query("select salesOrder from SalesOrder salesOrder where salesOrder.customerId=:id ")
    List findOrdersByCustomerId(Long id);


    @Modifying
    @Transactional
    @Query("update SalesOrder salesOrder set salesOrder.orderActive = 'INACTIVE' where salesOrder.id = :id")
    void deactivateOrderById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update SalesOrder salesOrder set salesOrder.totalPrice=:totalPrice, " +
            "salesOrder.accountingCode=:accountingCode where salesOrder.id=:salesOrderId")
    void saveTotalPriceAndAccountingCode(@Param("accountingCode") Long accountingCode
            , @Param("totalPrice") double totalPrice, @Param("salesOrderId") Long salesOrderId);

    @Modifying
    @Transactional
    @Query("update SalesOrder salesOrder set salesOrder.paymentCode=:paymentCode where salesOrder.id=:salesOrderId")
    void savePaymentCode(@Param("paymentCode") Long paymentCode, @Param("salesOrderId") Long salesOrderId);

    @Modifying
    @Transactional
    @Query("update SalesOrder salesOrder set salesOrder.orderItemList=:orderItemList," +
            "salesOrder.transport=:transport,salesOrder.customerAddress=:customerAddress" +
            " where salesOrder.id=:salesOrderId")
    void updateCart(@Param("customerAddress") String customerAddress, @Param("salesOrderId") Long salesOrderId,
                     @Param("transport") String transport,@Param("orderItemList") Set<OrderItem> orderItemList);


}
