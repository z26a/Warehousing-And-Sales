package ir.co.isc.salesorder.controller;


import ir.co.isc.salesorder.model.OrderItem;
import ir.co.isc.salesorder.model.SalesOrder;
import ir.co.isc.salesorder.repository.SalesOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value="/api/accounting/",method = {RequestMethod.GET,RequestMethod.DELETE})
public class AccountingController {

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @PostMapping(path="/checkProductActivity")
    public Object checkProductAvailability(@RequestBody SalesOrder salesOrder) {
        try{
            throw new UnsupportedOperationException("Not supported yet."); //Require accounting service.
        }catch (Exception e){
            log.error("Error while checking the availability of items: " + e.getMessage() );
            System.err.println(e.getMessage());
        }

        return null;
    }

}
