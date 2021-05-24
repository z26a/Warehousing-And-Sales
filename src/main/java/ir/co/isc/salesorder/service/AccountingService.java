package ir.co.isc.salesorder.service;

import ir.co.isc.salesorder.dto.AccountingDTO;
import ir.co.isc.salesorder.model.SalesOrder;
import ir.co.isc.salesorder.repository.SalesOrderRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@NoArgsConstructor
public class AccountingService {

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Object getCartTotalPrice(SalesOrder salesOrder){



        AccountingDTO accountingDTO =new AccountingDTO();
        Long salesOrderId= accountingDTO.getSalesOrderId();
        Long totalPrice= accountingDTO.getTotalPrice();
        Long accountingCode= accountingDTO.getAccountingCode();

        salesOrderRepository.saveTotalPriceAndAccountingCode(accountingCode,totalPrice,salesOrderId);

        throw new UnsupportedOperationException("Not supported yet."); //Require accounting service.
    }

}
