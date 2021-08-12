package kafka;

import order.domain.Customer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document
public class ShoppingCartDTO {
    @Id
    private Long id;
    private Customer customer;
    ArrayList<CartLine> cartlineList = new ArrayList<CartLine>();

    public ShoppingCartDTO() {
    }

    public ShoppingCartDTO(Long id, Customer customer, ArrayList<CartLine> cartlineList) {
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


    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (CartLine cline : cartlineList) {
            totalPrice = totalPrice + (cline.getProduct().getPrice() * cline.getQuantity());
        }
        return totalPrice;
    }
}
