package ir.co.isc.salesorder.service;

import ir.co.isc.salesorder.dto.AccountingDTO;
import ir.co.isc.salesorder.dto.PaymentDTO;
import ir.co.isc.salesorder.model.SalesOrder;
import ir.co.isc.salesorder.repository.SalesOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

@Autowired
private SalesOrderRepository salesOrderRepository;

    public Object payOrder(SalesOrder salesOrder){

        PaymentDTO paymentDTO=new PaymentDTO();
        Long accountingCode =paymentDTO.getPaymentCode();
        Long salesOrderId= paymentDTO.getSalesOrderId();

        salesOrderRepository.savePaymentCode(accountingCode,salesOrderId);

        throw new UnsupportedOperationException("Not supported yet."); //Require payment service.
    }

}
