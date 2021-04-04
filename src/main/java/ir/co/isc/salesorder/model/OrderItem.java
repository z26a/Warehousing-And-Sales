package ir.co.isc.salesorder.model;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Slf4j
@Getter
@Setter
public class OrderItem {

//    @EmbeddedId
//    @JsonIgnore
//    private OrderItemPrimaryKey pk;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;

//    @Id
//    @GeneratedValue
    private Long productId;

//    @Column(nullable = false)
//    private String itemName;

    @Column(nullable = false)
    private long productCount;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private SalesOrder salesOrder;
}
