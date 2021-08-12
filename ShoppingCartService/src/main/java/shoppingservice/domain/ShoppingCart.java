package shoppingservice.domain;

import kafka.CustomerAdapter;
import kafka.ShoppingCartRecord;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Iterator;

@Document
public class ShoppingCart {
    @Id
    private Long id;
    private Customer customer;
    ArrayList<CartLine> cartlineList = new ArrayList<CartLine>();

    public ShoppingCart() {
    }

    public ShoppingCart(Long id, Customer customer, ArrayList<CartLine> cartlineList) {
        this.id = id;
        this.customer = customer;
        this.cartlineList = cartlineList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArrayList<CartLine> getCartlineList() {
        return cartlineList;
    }

    public void setCartlineList(ArrayList<CartLine> cartlineList) {
        this.cartlineList = cartlineList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addToCart(Product product, int quantity) {
        for (CartLine cline : cartlineList) {
            if (cline.getProduct().getProductNumber().equals(product.getProductNumber())) {
                cline.setQuantity(cline.getQuantity() + quantity);
                return;
            }
        }
        CartLine cline = new CartLine();
        cline.setProduct(product);
        cline.setQuantity(quantity);
        cartlineList.add(cline);
    }

    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (CartLine cline : cartlineList) {
            totalPrice = totalPrice + (cline.getProduct().getPrice() * cline.getQuantity());
        }
        return totalPrice;
    }

    public void removeFromCart(Product product) {
        Iterator<CartLine> iter = cartlineList.iterator();
        while (iter.hasNext()) {
            CartLine cline = iter.next();
            if (cline.getProduct().getProductNumber().equals(product.getProductNumber())) {
                if (cline.getQuantity() > 1) {
                    cline.setQuantity(cline.getQuantity() - 1);
                } else {
                    iter.remove();
                }
            }
        }
    }

    @Override
    public String toString() {
        return "ShoppingCartRecord{" +
                "id=" + id +
                ", customer=" + customer +
                ", cartlineList=" + cartlineList +
                '}';
    }
}
