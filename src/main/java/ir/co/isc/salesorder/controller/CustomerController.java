package ir.co.isc.salesorder.controller;

import ir.co.isc.salesorder.model.OrderItem;
import ir.co.isc.salesorder.model.SalesOrder;
import ir.co.isc.salesorder.repository.OrderItemRepository;
import ir.co.isc.salesorder.repository.SalesOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Access;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping(value="/api/orders",method = {RequestMethod.GET,RequestMethod.DELETE})
public class CustomerController {
    @Autowired
    private SalesOrderRepository salesOrderRepository;


    @GetMapping(path="/{id}")
    public Object getAllOrdersOfCustomer(@PathVariable(name = "id") String id) {
        try{
           if(salesOrderRepository.findOrdersByCustomerId(Long.valueOf(id)).isEmpty()){
               return "No orders for user "+id;
           }
           else
               return salesOrderRepository.findOrdersByCustomerId(Long.valueOf(id));
        }catch (Exception e){
            log.error( "Error while getting orders of customer with customer id " + e.getMessage() );
            System.err.println(e.getMessage());
            return null;
        }
    }


    @GetMapping(path="/get-order/{orderId}")
    public Object getOrderById(@PathVariable(name="orderId") String orderId){
        try{
            return salesOrderRepository.findById(Long.valueOf(orderId));
        }catch (Exception e){
            log.error("Error while getting an order by orderId "+e.getMessage());
            System.err.println(e.getMessage());
            return null;
        }
    }


    @DeleteMapping(path="/delete-order/{orderId}")
    public Object deleteOrderById(@PathVariable(name="orderId") String orderId){
        try{
        salesOrderRepository.deleteById(Long.valueOf(orderId));
        log.info("Order deleted successfully");
        return "Order deleted successfully";
        }catch (Exception e){
            log.error("Error while deleting an order: " + e.getMessage() );
            System.err.println(e.getMessage());
            return null;
        }
    }

//    @PostMapping(path="/sell")
//        public @ResponseBody Object saveSellOrder(@RequestBody Map<String,Object> payload){
//
//        }

    @RequestMapping(path="/buy")
    public Object saveBuyOrder(@RequestParam(name="customerId") String customerId,
                               @RequestParam(name="customerAdd") String customerAdd,
                               @RequestBody List<OrderItem> orderItemList)
                               {
        SalesOrder salesOrder=new SalesOrder(Long.valueOf(customerId),customerAdd);
        try {
            for (OrderItem item : orderItemList) {
                salesOrder.addItem(item);
            }
            return "Your order has been successfully registered";
        }catch (Exception e){
            log.error("Error while saving an order: " + e.getMessage() );
            System.err.println(e.getMessage());
            return null;
        }
    }





}