package shoppingservice.service;

import shoppingservice.domain.CartLine;
import shoppingservice.domain.ShoppingCart;

public class ShoppingCartAdapter {
    public static ShoppingCart getShoppingCartFromShoppingCartDTO(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(shoppingCartDTO.getId());
        for (CartLineDTO cartLine : shoppingCartDTO.getCartlineList()) {
            shoppingCart.addToCart(ProductAdapter.getProductFromProductDTO(cartLine.getProduct()), cartLine.getQuantity());
        }
        return shoppingCart;
    }

    public static ShoppingCartDTO getShoppingCartDTOFromShoppingCart(ShoppingCart shoppingCart) {
        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        shoppingCartDTO.setId(shoppingCart.getId());
        for (CartLine cartLine : shoppingCart.getCartlineList()) {
            CartLineDTO cartLineDTO = new CartLineDTO();
            cartLineDTO.setQuantity(cartLine.getQuantity());
            cartLineDTO.setProduct(ProductAdapter.getProductDTOFromProduct(cartLine.getProduct()));
            shoppingCartDTO.addCartLine(cartLineDTO);
        }
        return shoppingCartDTO;
    }
}
