package ir.co.isc.salesorder.dto;

import ir.co.isc.salesorder.StatusCodes;
import ir.co.isc.salesorder.model.SalesOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteOrderByIdResponse {
    private StatusCodes statusCode;
    private String description;
    public  String results;
}
