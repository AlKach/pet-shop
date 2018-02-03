package by.kachanov.shop.dto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product extends Item {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "products_categories",
            joinColumns = { @JoinColumn(name = "product_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") })
    private Set<Category> categories = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Product product = (Product) o;

        if (getId() != null ? !getId().equals(product.getId()) : product.getId() != null)
            return false;
        if (getName() != null ? !getName().equals(product.getName()) : product.getName() != null)
            return false;
        if (getDescription() != null
                ? !getDescription().equals(product.getDescription())
                : product.getDescription() != null)
            return false;
        return getPrice() != null ? getPrice().equals(product.getPrice()) : product.getPrice() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        return result;
    }
}
