package ir.co.isc.salesorder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetrievePriceDTO {

    @NonNull
    private Long salesOrderId;


}
