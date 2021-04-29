package ir.co.isc.salesorder.service;

import ir.co.isc.salesorder.dto.SalesOrderDTO;
import ir.co.isc.salesorder.model.SalesOrder;
import ir.co.isc.salesorder.repository.SalesOrderRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@NoArgsConstructor
public class AccountingService {

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    public Object getCartTotalPrice(SalesOrder salesOrder){

        SalesOrderDTO salesOrderDTO=new SalesOrderDTO();
        Long salesOrderId=salesOrderDTO.getSalesOrderId();
        Double totalPrice=salesOrderDTO.getTotalPrice();
        Long accountingCode=salesOrderDTO.getAccountingCode();

        salesOrderRepository.saveTotalPriceAndAccountingCode(accountingCode,totalPrice,salesOrderId);

        throw new UnsupportedOperationException("Not supported yet."); //Require accounting service.
    }

}
