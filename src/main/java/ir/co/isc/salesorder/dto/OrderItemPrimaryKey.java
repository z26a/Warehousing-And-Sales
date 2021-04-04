package ir.co.isc.salesorder.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ir.co.isc.salesorder.model.SalesOrder;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class OrderItemPrimaryKey implements Serializable {

    public class OrderProductPK  {

        @JsonBackReference
        @ManyToOne(optional = false, fetch = FetchType.LAZY)
        @JoinColumn(name = "order_id")
        private SalesOrder order;

//        @ManyToOne(optional = false, fetch = FetchType.LAZY)
//        @JoinColumn(name = "product_id")
//        private Product product;

    }
}
