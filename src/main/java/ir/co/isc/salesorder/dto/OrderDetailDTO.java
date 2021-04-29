package ir.co.isc.salesorder.dto;

import ir.co.isc.salesorder.model.OrderItem;
import lombok.Data;

import java.util.List;

@Data
public class OrderDetailDTO {

    private double totalPrice ;

    private double totalWeight ;

    private List<OrderItemDTO> orderItemDTOList;

}
