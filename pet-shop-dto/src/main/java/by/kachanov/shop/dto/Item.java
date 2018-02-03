package by.kachanov.shop.dto;

import javax.persistence.*;
import java.math.BigInteger;

@MappedSuperclass
public abstract class Item {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk")
    @SequenceGenerator(name = "pk", sequenceName = "pk_seq", allocationSize = 1)
    private BigInteger id;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }
}
