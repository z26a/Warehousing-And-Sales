package ir.co.isc.salesorder.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.co.isc.salesorder.ProductAvailability;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private long productCount;

    @Column
    private String productName;

    @Column
    private ProductAvailability productAvailability;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "salesOrder_id", referencedColumnName = "id", nullable = false)
    private SalesOrder salesOrder;


    public OrderItem(Long productId, long productCount) {
        this.productId = productId;
        this.productCount = productCount;
    }


}


