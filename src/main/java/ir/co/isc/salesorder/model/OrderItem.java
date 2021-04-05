package ir.co.isc.salesorder.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Slf4j
@Data
@Component
public class OrderItem {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;


    @Column(nullable = false)
    private long productCount;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private SalesOrder salesOrder;

    public OrderItem() {

    }

    @Autowired
    public OrderItem(Long productId, long productCount, SalesOrder salesOrder) {
        this.productId = productId;
        this.productCount = productCount;
        this.salesOrder = salesOrder;
    }

//    public void setSalesOrder(SalesOrder salesOrder) {
//        this.salesOrder = salesOrder;
//    }
}


