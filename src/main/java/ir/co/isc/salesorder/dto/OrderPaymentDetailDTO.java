package ir.co.isc.salesorder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderPaymentDetailDTO {
    @NonNull
    private Long salesOrderId;

    @NonNull
    private Long totalPrice;
}
