package shoppingservice.service;

import java.util.ArrayList;

public class ShoppingCartDTO {
    private Long id;

    ArrayList<CartLineDTO> cartlineList = new ArrayList<CartLineDTO>();

    public ShoppingCartDTO() {
    }

    public ShoppingCartDTO(Long id, ArrayList<CartLineDTO> cartlineList) {
        this.id = id;
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

    public void addCartLine(CartLineDTO cartLine) {
        cartlineList.add(cartLine);
    }
}
