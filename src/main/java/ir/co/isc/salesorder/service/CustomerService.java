package ir.co.isc.salesorder.service;

import ir.co.isc.salesorder.OrderActive;
import ir.co.isc.salesorder.StatusCodes;
import ir.co.isc.salesorder.dto.*;
import ir.co.isc.salesorder.model.OrderItem;
import ir.co.isc.salesorder.model.SalesOrder;
import ir.co.isc.salesorder.repository.OrderItemRepository;
import ir.co.isc.salesorder.repository.SalesOrderRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@Service
@NoArgsConstructor
@Slf4j
public class CustomerService {

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private RestTemplate restTemplate;

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

        public DeleteOrderByIdResponse deleteOrderById (Long orderId) {
            try {
                DeleteOrderByIdResponse deleteOrderByIdResponse = new DeleteOrderByIdResponse();
                if (salesOrderRepository.findById(orderId).isPresent()) {
                    if (salesOrderRepository.findById(orderId).get().getOrderActive() == OrderActive.ACTIVE) {
                        salesOrderRepository.deactivateOrderById(orderId);
                        log.info("Order deleted successfully");
                        deleteOrderByIdResponse.setStatusCode(StatusCodes.OK);
                        deleteOrderByIdResponse.setResults("Order deleted successfully");

                    } else {
                        deleteOrderByIdResponse.setStatusCode(StatusCodes.REQUEST_PROCESSING_ERROR);
                        deleteOrderByIdResponse.setResults("No order with provided id exists");
                    }

                }
                else {
                    deleteOrderByIdResponse.setStatusCode(StatusCodes.REQUEST_PROCESSING_ERROR);
                    deleteOrderByIdResponse.setResults("No order with provided id exists");
                }
                return deleteOrderByIdResponse;
            } catch (Exception e) {
                log.error("Error while deleting an order: " + e.getMessage());
                DeleteOrderByIdResponse deleteOrderByIdResponse = new DeleteOrderByIdResponse();
                deleteOrderByIdResponse.setStatusCode(StatusCodes.REQUEST_PROCESSING_ERROR);
                deleteOrderByIdResponse.setDescription("");
                deleteOrderByIdResponse.setResults("Error while deleting an order by orderId " + orderId);
                return deleteOrderByIdResponse;
            }
        }
        public Object saveBuyOrder (CartDTO cartDTO){

                SalesOrder salesOrder = new SalesOrder();
                OrderItemDTO orderItemDTO=new OrderItemDTO();
                List<OrderItemDTO> orderItemDTOList=new ArrayList<>();
                salesOrder.setCustomerId(Long.valueOf(cartDTO.getCustomerId()));
                salesOrder.setCustomerAddress(cartDTO.getCustomerAddress());
                salesOrder.setTransport(cartDTO.getTransport());
                salesOrder.setOrderActive(OrderActive.ACTIVE);
                for (OrderItem item : cartDTO.getOrderItemList()) {
                    salesOrder.addItem(item);
                    orderItemDTO.setId(item.getProductId());
                    orderItemDTO.setItemQuantity(item.getProductCount());
                    orderItemDTOList.add(orderItemDTO);
                }
            try {
                // check availability of items using an api from warehouse
//                throw new UnsupportedOperationException("Not supported yet."); //Require warehouse service.
                // check viability of moving the items using an api from delivery
//                throw new UnsupportedOperationException("Not supported yet."); //Require warehouse service.

                salesOrder=salesOrderRepository.save(salesOrder);

                // ask accounting service to compute total price

                OrderDetailDTO orderDetailDTO=new OrderDetailDTO(salesOrder.getId(),orderItemDTOList);

                AccountingDTO accountingDTO=restTemplate.postForObject("",orderDetailDTO,AccountingDTO.class);
                assert accountingDTO != null;
                salesOrderRepository.saveTotalPriceAndAccountingCode(accountingDTO.getAccountingCode(),accountingDTO.getTotalPrice(),salesOrder.getId());

                return "Your order has been successfully registered";

            }catch(Exception e){
                log.error("Error while processing the order " + e.getMessage() );
                return "A problem occurred while processing your order";
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
