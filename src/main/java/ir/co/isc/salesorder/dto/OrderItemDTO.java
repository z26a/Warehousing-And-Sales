package ir.co.isc.salesorder.dto;


import ir.co.isc.salesorder.model.SalesOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Slf4j
@Getter
@Setter
public class OrderItemDTO {

    @Column(nullable = false)
    private Long id;

//    @Column(nullable = false)
//    private String itemName;

    @Column(nullable = false)
    private long itemQuantity;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private SalesOrder salesOrder;
}
