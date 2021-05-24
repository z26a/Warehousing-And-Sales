package ir.co.isc.salesorder.service;

import ir.co.isc.salesorder.dto.OrderPaymentDetailDTO;
import ir.co.isc.salesorder.dto.PaymentDTO;
import ir.co.isc.salesorder.model.SalesOrder;
import ir.co.isc.salesorder.repository.SalesOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {

@Autowired
private SalesOrderRepository salesOrderRepository;

@Autowired
private RestTemplate restTemplate;

    public Object payOrder(OrderPaymentDetailDTO orderPaymentDetailDTO){

        PaymentDTO paymentDTO=restTemplate.postForObject("",orderPaymentDetailDTO,PaymentDTO.class);

        Long paymentCode =paymentDTO.getPaymentCode();
        Long salesOrderId= paymentDTO.getSalesOrderId();

        salesOrderRepository.savePaymentCode(paymentCode,salesOrderId);

        throw new UnsupportedOperationException("Not supported yet."); //Require payment service.
    }

}
