package ir.co.isc.salesorder.controller;

import ir.co.isc.salesorder.dto.OrderPaymentDetailDTO;
import ir.co.isc.salesorder.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping(path="api/order/pay-order")
    public Object payOrder(@RequestBody OrderPaymentDetailDTO orderPaymentDetailDTO) {
        try{
            paymentService.payOrder(orderPaymentDetailDTO);
            throw new UnsupportedOperationException("Not supported yet."); //Require accounting service.
            // call an api from accounting service to send payment code to it
        }catch (Exception e){
            log.error("Error while paying the order " + e.getMessage() );
        }
        return null;
    }
}
