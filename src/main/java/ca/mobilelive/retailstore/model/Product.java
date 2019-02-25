package ca.mobilelive.retailstore.model;

import javax.persistence.*;

/**
 * Created by Noush on 10/25/2018.
 */
@Entity
public class Product {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String description;
    private int quantity;
    private String unitPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return String.format(
                "Product[id=%d, name='%s', description='%s', quantity=%d, unitPrice='%s']",
                id, name, description, quantity, unitPrice);
    }
}
