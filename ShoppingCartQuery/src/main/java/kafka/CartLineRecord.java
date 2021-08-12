package kafka;

public class CartLineRecord {
    int quantity;
    ProductDTO product;

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
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
        return "CartLineRecord{" +
                "quantity=" + quantity +
                ", product=" + product +
                '}';
    }
}
