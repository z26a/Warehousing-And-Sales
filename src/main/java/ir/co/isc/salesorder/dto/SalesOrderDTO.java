package ir.co.isc.salesorder.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
public class SalesOrderDTO {

    @NonNull
    private Long salesOrderId;

    @NonNull
    private Long accountingCode;

    @NonNull
    private double totalPrice;
}
