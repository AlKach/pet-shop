package by.kachanov.shop.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orders_products")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class OrderPosition extends Item {

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity")
    private BigDecimal quantity;

}
