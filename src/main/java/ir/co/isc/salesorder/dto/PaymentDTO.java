package ir.co.isc.salesorder.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
public class PaymentDTO {

    private Long salesOrderId;

    private Long paymentCode;

    private String paymentDetail;

}
