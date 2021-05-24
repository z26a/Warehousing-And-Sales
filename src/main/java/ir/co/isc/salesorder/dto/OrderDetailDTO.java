package ir.co.isc.salesorder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {

    @NonNull
    private Long salesOrderId;

    private List<OrderItemDTO> orderItemDTOList;

}
