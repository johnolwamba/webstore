package kafka;


import java.util.ArrayList;

public class ShoppingCartRecord {
    private Long id;
    private CustomerDTO customer;
    ArrayList<CartLineRecord> cartlineList = new ArrayList<CartLineRecord>();

    public ShoppingCartRecord() {
    }

    public ShoppingCartRecord(Long id, CustomerDTO customer, ArrayList<CartLineRecord> cartlineList) {
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

    public ArrayList<CartLineRecord> getCartlineList() {
        return cartlineList;
    }

    public void setCartlineList(ArrayList<CartLineRecord> cartlineList) {
        this.cartlineList = cartlineList;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public void addCartLine(CartLineRecord cartLine) {
        cartlineList.add(cartLine);
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
