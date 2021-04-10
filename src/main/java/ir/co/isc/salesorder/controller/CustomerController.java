
package ir.co.isc.salesorder.controller;

import ir.co.isc.salesorder.dto.CartDTO;
import ir.co.isc.salesorder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(value="/api/orders",method = {RequestMethod.GET,RequestMethod.DELETE})
@Slf4j
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    @GetMapping(path="/{id}")
    public Object getAllOrdersOfCustomer(@PathVariable(name = "id") String id) {
       return customerService.getAllOrdersOfCustomer(id);
    }


    @GetMapping(path="/get-order/{orderId}")
    public Object getOrderById(@PathVariable(name="orderId") String orderId){
return customerService.getOrderById(orderId);
    }


    @PutMapping(path="/delete-order/{orderId}")
    public Object deleteOrderById(@PathVariable(name="orderId") String orderId){
return customerService.deleteOrderById(orderId);
    }


    @PostMapping(path="/buy")
    public Object saveBuyOrder(
            @RequestBody CartDTO cartDTO, HttpServletResponse response)
    {
        return  customerService.saveBuyOrder(cartDTO,response);
    }

}