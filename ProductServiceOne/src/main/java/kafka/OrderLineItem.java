package kafka;


public class OrderLineItem {
    int quantity;
    Product product;

    public OrderLineItem() {
    }

    public OrderLineItem(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderLineItem{" +
                "quantity=" + quantity +
                ", product=" + product +
                '}';
    }
}
