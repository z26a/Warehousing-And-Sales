package ir.co.isc.salesorder.controller;

import ir.co.isc.salesorder.model.SalesOrder;
import ir.co.isc.salesorder.service.AccountingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping(value="/orders/accounting/",method = {RequestMethod.GET,RequestMethod.DELETE})
public class AccountingController {

    @Autowired
    private AccountingService accountingService;

    @PostMapping(path="/orders/cart-total-price")
    public Object getCartTotalPrice(@RequestBody SalesOrder salesOrder) {
        try{
            accountingService.getCartTotalPrice(salesOrder);
        }catch (Exception e){
            log.error("Error while getting the total price of cart: " + e.getMessage() );
        }
        return null;
    }





}
