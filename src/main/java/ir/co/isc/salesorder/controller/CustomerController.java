
package ir.co.isc.salesorder.controller;

import ir.co.isc.salesorder.dto.CartDTO;
import ir.co.isc.salesorder.dto.DeleteOrderByIdResponse;
import ir.co.isc.salesorder.dto.UpdatedCartDTO;
import ir.co.isc.salesorder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(value="/orders",method = {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST})
@Slf4j
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    @GetMapping(path="/orders/{id}")
    public Object getAllOrdersOfCustomer(@PathVariable(name = "id") Long id) {
        try {
            return customerService.getAllOrdersOfCustomer(id);
        } catch (Exception e) {
            log.error("Error while getting orders of customer with customer id " + e.getMessage());
            return null;
        }

    }


    @GetMapping(path="/get-order/{orderId}")
    public Object getOrderById(@PathVariable(name="orderId") String orderId){
return customerService.getOrderById(Long.valueOf(orderId));
    }


    @PutMapping(path="/delete-order/{orderId}")
    public DeleteOrderByIdResponse deleteOrderById(@PathVariable(name="orderId") String orderId){
return customerService.deleteOrderById(Long.valueOf(orderId));
    }


    @PostMapping(path="/buy")
    public Object saveBuyOrder(
            @RequestBody CartDTO cartDTO, HttpServletResponse response)
    {
        try {
            return  customerService.saveBuyOrder(cartDTO);
        } catch (Exception e) {
            log.error("Error while saving an order: " + e.getMessage());
            return null;
        }

    }

    @PutMapping(path="/modify-cart/{orderId}")
        public Object updateBuyOrder(@PathVariable(name="orderId") String orderId,
                                     @RequestBody UpdatedCartDTO updatedCartDTO,
                                     HttpServletResponse response){
            try{
                return customerService.updateBuyOrder(Long.valueOf(orderId),updatedCartDTO,response);
            }catch (Exception e){
                log.error("Error while updating an order: "+e.getMessage());
                return null;
            }


        }


}