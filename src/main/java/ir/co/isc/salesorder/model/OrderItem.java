package ir.co.isc.salesorder.model;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Slf4j
@Getter
@Setter
@Component
@NoArgsConstructor
public class OrderItem {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private long productCount;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "salesOrder_id", referencedColumnName = "id", nullable = false)
    private SalesOrder salesOrder;


    public OrderItem(Long productId, long productCount, SalesOrder salesOrder) {
        this.productId = productId;
        this.productCount = productCount;
        this.salesOrder = salesOrder;
    }


}


