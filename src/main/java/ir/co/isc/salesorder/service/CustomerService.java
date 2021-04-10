package ir.co.isc.salesorder.service;

import ir.co.isc.salesorder.OrderActive;
import ir.co.isc.salesorder.dto.CartDTO;
import ir.co.isc.salesorder.model.OrderItem;
import ir.co.isc.salesorder.model.SalesOrder;
import ir.co.isc.salesorder.repository.SalesOrderRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.ServiceMode;

@Service
@NoArgsConstructor
@Slf4j
public class CustomerService {

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    public Object getAllOrdersOfCustomer(String id) {

        try {
            if (salesOrderRepository.findOrdersByCustomerId(Long.valueOf(id)).isEmpty()) {
                return "No orders for user " + id;
            } else
                return salesOrderRepository.findOrdersByCustomerId(Long.valueOf(id));
        } catch (Exception e) {
            log.error("Error while getting orders of customer with customer id " + e.getMessage());
            return null;
        }

    }
        public Object getOrderById (String orderId){
            try {
                return salesOrderRepository.findById(Long.valueOf(orderId));
            } catch (Exception e) {
                log.error("Error while getting an order by orderId " + e.getMessage());
                return null;
            }
        }

        public Object deleteOrderById (String orderId){
            try {
                salesOrderRepository.deactivateOrderById(Long.valueOf(orderId));
                log.info("Order deleted successfully");
                return "Order deleted successfully";
            } catch (Exception e) {
                log.error("Error while deleting an order: " + e.getMessage());
                return null;
            }
        }

        public Object saveBuyOrder (CartDTO cartDTO, HttpServletResponse response){
            try {

                // check availability of items using an api from warehouse

                SalesOrder salesOrder = new SalesOrder();
                salesOrder.setCustomerId(Long.valueOf(cartDTO.getCustomerId()));
                salesOrder.setCustomerAddress(cartDTO.getCustomerAddress());
                salesOrder.setTransport(cartDTO.getTransport());
                salesOrder.setOrderActive(OrderActive.ACTIVE);
                for (OrderItem item : cartDTO.getOrderItemList()) {
                    salesOrder.addItem(item);
                }
                salesOrderRepository.save(salesOrder);
                return "Your order has been successfully registered";
            } catch (Exception e) {
                log.error("Error while saving an order: " + e.getMessage());

                return null;
            }
        }
    }
