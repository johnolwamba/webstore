package shoppingcartquery.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CartLine {
    int quantity;
    Product product;

    public CartLine() {
    }

    public CartLine(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartLine{" +
                "quantity=" + quantity +
                ", product=" + product +
                '}';
    }
}
