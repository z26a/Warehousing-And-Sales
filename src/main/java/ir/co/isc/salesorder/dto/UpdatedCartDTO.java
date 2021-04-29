package ir.co.isc.salesorder.dto;

import ir.co.isc.salesorder.model.OrderItem;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
@NoArgsConstructor
public class UpdatedCartDTO {

    private String customerAddress;

    private List<OrderItem> orderItemList;

    private String transport;
}
