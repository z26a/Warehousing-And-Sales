package ir.co.isc.salesorder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.co.isc.salesorder.DeliveryStatus;
import ir.co.isc.salesorder.OrderActive;
import ir.co.isc.salesorder.OrderType;
import ir.co.isc.salesorder.dto.OrderItemPrimaryKey;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Slf4j
@Getter
@Setter
public class SalesOrder {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long customerId;

    private String customerAddress;

    @Column(nullable = false)
    private LocalDate orderDate = LocalDate.now();

    private String orderDesc;
    private double totalPrice;
    private Long accountingCode;

    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
    private OrderType orderType;

    private Long paymentCode;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @Enumerated(EnumType.STRING)
    private OrderActive orderActive;

    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "salesOrder")
    private Set<OrderItem> orderItemList;

    public SalesOrder() {
    }

    public SalesOrder(Long customerId, String customerAddress) {
        this.customerId = customerId;
        this.customerAddress=customerAddress;
    }

    public void addItem(OrderItem orderItem) {
        if (orderItemList == null)
            orderItemList = new HashSet<OrderItem>();
        orderItem.setSalesOrder(this);
        orderItemList.add(orderItem);
    }
}
