package shoppingservice.service;

import java.util.ArrayList;

public class ShoppingCartDTO {
    private Long id;
    private CustomerDTO customer;
    ArrayList<CartLineDTO> cartlineList = new ArrayList<CartLineDTO>();

    public ShoppingCartDTO() {
    }

    public ShoppingCartDTO(Long id, CustomerDTO customer, ArrayList<CartLineDTO> cartlineList) {
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

    public ArrayList<CartLineDTO> getCartlineList() {
        return cartlineList;
    }

    public void setCartlineList(ArrayList<CartLineDTO> cartlineList) {
        this.cartlineList = cartlineList;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public void addCartLine(CartLineDTO cartLine) {
        cartlineList.add(cartLine);
    }

    @Override
    public String toString() {
        return "ShoppingCartDTO{" +
                "id=" + id +
                ", customer=" + customer +
                ", cartlineList=" + cartlineList +
                '}';
    }
}
