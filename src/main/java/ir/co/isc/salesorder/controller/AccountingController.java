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
@RequestMapping(value="/api/accounting/")
public class AccountingController {

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @PostMapping(path="/productInformation")
    public Object sendProductInfo(@RequestBody List<OrderItem> orderItemList,@RequestParam(name="transport")) {

    }

}
