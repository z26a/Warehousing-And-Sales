package ir.co.isc.salesorder.model;

import ir.co.isc.salesorder.OrderActive;
import ir.co.isc.salesorder.OrderType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Slf4j
@Getter
@Setter
@Component
@NoArgsConstructor
public class SalesOrder {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private String customerAddress;

    @Column(nullable = false)
    private LocalDate orderDate = LocalDate.now();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderActive orderActive;

    private String orderDesc;

    private double totalPrice;

    private Long accountingCode;

//    @Enumerated(EnumType.STRING)
//    private OrderType orderType;

    private Long paymentCode;

    private int status;

    private int activityCheck;

    private String transport;

    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "salesOrder")
    private Set<OrderItem> orderItemList;

    public SalesOrder(Long customerId, String customerAddress) {
        this.customerId = customerId;
        this.customerAddress=customerAddress;
    }

    public void addItem(OrderItem orderItem) {
        if (orderItemList == null)
            orderItemList = new HashSet<>();
        orderItem.setSalesOrder(this);
        orderItemList.add(orderItem);
    }
}
