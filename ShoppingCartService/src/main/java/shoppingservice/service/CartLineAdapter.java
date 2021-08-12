package shoppingservice.service;

import shoppingservice.domain.CartLine;

public class CartLineAdapter {
    public static CartLine getCartLineFromCartLineDTO(CartLineDTO cartLineDTO) {
        CartLine cartLine = new CartLine();
        cartLine.setQuantity(cartLineDTO.getQuantity());
        cartLine.setProduct(ProductAdapter.getProductFromProductDTO(cartLineDTO.getProduct()));
        return cartLine;
    }

    public static CartLineDTO getCartLineDTOFromCartLine(CartLine cartLine) {
        CartLineDTO cartLineDTO = new CartLineDTO();
        cartLineDTO.setQuantity(cartLine.getQuantity());
        cartLineDTO.setProduct(ProductAdapter.getProductDTOFromProduct(cartLine.getProduct()));
        return cartLineDTO;
    }
}
