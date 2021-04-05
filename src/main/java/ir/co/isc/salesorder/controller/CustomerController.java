package ir.co.isc.salesorder.controller;

import ir.co.isc.salesorder.dto.CartDTO;
import ir.co.isc.salesorder.model.OrderItem;
import ir.co.isc.salesorder.model.SalesOrder;
import ir.co.isc.salesorder.repository.SalesOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping(value="/api/orders",method = {RequestMethod.GET,RequestMethod.DELETE})
@Slf4j
public class CustomerController {


    private final SalesOrderRepository salesOrderRepository;

    @Autowired
    public CustomerController(SalesOrderRepository salesOrderRepository) {
        this.salesOrderRepository = salesOrderRepository;
    }

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
            return null;
        }
    }


    @GetMapping(path="/get-order/{orderId}")
    public Object getOrderById(@PathVariable(name="orderId") String orderId){
        try{
            return salesOrderRepository.findById(Long.valueOf(orderId));
        }catch (Exception e){
            log.error("Error while getting an order by orderId "+e.getMessage());
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
            return null;
        }
    }


    @PostMapping(path="/buy")
    public Object saveBuyOrder(
            @RequestBody CartDTO cartDTO)
    {

        try {
            SalesOrder salesOrder=new SalesOrder();
            salesOrder.setCustomerId(Long.getLong(cartDTO.getCustomerId()));
            for (OrderItem item : cartDTO.getOrderItem()) {
                salesOrder.addItem(item);
            }
            salesOrderRepository.save(salesOrder);
            return "Your order has been successfully registered";
        }catch (Exception e){
            log.error("Error while saving an order: " + e.getMessage() );
            return null;
        }
    }





}