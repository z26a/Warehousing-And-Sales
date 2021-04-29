package ir.co.isc.salesorder.service;

import ir.co.isc.salesorder.OrderActive;
import ir.co.isc.salesorder.StatusCodes;
import ir.co.isc.salesorder.dto.CartDTO;
import ir.co.isc.salesorder.dto.DeleteOrderByIdResponse;
import ir.co.isc.salesorder.dto.GetOrderByIdResponse;
import ir.co.isc.salesorder.dto.UpdatedCartDTO;
import ir.co.isc.salesorder.model.OrderItem;
import ir.co.isc.salesorder.model.SalesOrder;
import ir.co.isc.salesorder.repository.OrderItemRepository;
import ir.co.isc.salesorder.repository.SalesOrderRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.ServiceMode;
import java.util.Optional;

@Service
@NoArgsConstructor
@Slf4j
public class CustomerService {

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public Object getAllOrdersOfCustomer(Long id) {

            if (salesOrderRepository.findOrdersByCustomerId(id).isEmpty()) {
                return "No orders for user " + id;
            } else
                return salesOrderRepository.findOrdersByCustomerId(id);

    }
        public Object getOrderById (Long orderId){
            try {

             SalesOrder salesOrderResponse= salesOrderRepository.findById(orderId).get();
             GetOrderByIdResponse getOrderByIdResponse=new  GetOrderByIdResponse();
             getOrderByIdResponse.setSalesOrder(salesOrderResponse);
             getOrderByIdResponse.setStatusCode(StatusCodes.OK);
                return getOrderByIdResponse;

            } catch (Exception e) {
                log.error("Error while getting an order by orderId " + e.getMessage());
                GetOrderByIdResponse getOrderByIdResponse=new  GetOrderByIdResponse();
                getOrderByIdResponse.setStatusCode(StatusCodes.REQUEST_PROCESSING_ERROR);
                getOrderByIdResponse.setDescription("");
                getOrderByIdResponse.setResults("Error while getting an order by orderId "+orderId);
                return getOrderByIdResponse;
            }
        }

        public Object deleteOrderById (Long orderId){
            try {
                salesOrderRepository.deactivateOrderById(orderId);
                DeleteOrderByIdResponse deleteOrderByIdResponse=new DeleteOrderByIdResponse();
                log.info("Order deleted successfully");
                deleteOrderByIdResponse.setStatusCode(StatusCodes.OK);
                deleteOrderByIdResponse.setResults("Order deleted successfully");
                return deleteOrderByIdResponse;
            } catch (Exception e) {
                log.error("Error while deleting an order: " + e.getMessage());
                DeleteOrderByIdResponse deleteOrderByIdResponse=new DeleteOrderByIdResponse();
                deleteOrderByIdResponse.setStatusCode(StatusCodes.REQUEST_PROCESSING_ERROR);
                deleteOrderByIdResponse.setDescription("");
                deleteOrderByIdResponse.setResults("Error while deleting an order by orderId "+orderId);
                return deleteOrderByIdResponse;
            }
        }

        public Object saveBuyOrder (CartDTO cartDTO){

                SalesOrder salesOrder = new SalesOrder();
                salesOrder.setCustomerId(Long.valueOf(cartDTO.getCustomerId()));
                salesOrder.setCustomerAddress(cartDTO.getCustomerAddress());
                salesOrder.setTransport(cartDTO.getTransport());
                salesOrder.setOrderActive(OrderActive.ACTIVE);
                for (OrderItem item : cartDTO.getOrderItemList()) {
                    salesOrder.addItem(item);
                }
            try {
                // check availability of items using an api from warehouse
//                throw new UnsupportedOperationException("Not supported yet."); //Require warehouse service.
                // check viability of moving the items using an api from delivery
//                throw new UnsupportedOperationException("Not supported yet."); //Require warehouse service.
                salesOrderRepository.save(salesOrder);
                return "Your order has been successfully registered";

            }catch(Exception e){
                log.error("Error while processing the order " + e.getMessage() );
                return "A problem while processing your order";
            }

        }

        public Object updateBuyOrder(Long orderId,UpdatedCartDTO updatedCartDTO, HttpServletResponse response){
            SalesOrder salesOrder = salesOrderRepository.findById(orderId).
                    orElseThrow(() -> new EntityNotFoundException(orderId.toString()));
            salesOrder.setTransport(updatedCartDTO.getTransport());
            salesOrder.setCustomerAddress(updatedCartDTO.getCustomerAddress());
            salesOrder.setOrderItemList(null);
            for (OrderItem item:updatedCartDTO.getOrderItemList()){
                salesOrder.addItem(item);
            }
            try {
                // check availability of items using an api from warehouse
                // throw new UnsupportedOperationException("Not supported yet."); //Require warehouse service.
                // check viability of moving the items using an api from delivery

                salesOrderRepository.deleteById(orderId);
                orderItemRepository.deleteAllBySalesOrderId(orderId);
                salesOrderRepository.save(salesOrder);
                response.setStatus(HttpServletResponse.SC_OK);
                return "Your order has been successfully updated";

            }catch(Exception e){
                log.error("Error while updating the order " + e.getMessage() );
                return "Error while processing your order";
            }



        }
    }
