package by.kachanov.shop.dto;

import javax.persistence.*;
import java.math.BigInteger;

import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class Item {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk")
    @SequenceGenerator(name = "pk", sequenceName = "pk_seq", allocationSize = 1)
    private BigInteger id;
}
