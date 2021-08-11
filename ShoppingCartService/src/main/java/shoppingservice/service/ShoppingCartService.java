package shoppingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shoppingservice.data.ShoppingCartRepository;
import shoppingservice.domain.Product;
import shoppingservice.domain.ShoppingCart;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ShoppingCartService {
    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    public void addToCart(Long cartId, ProductDTO productDto, int quantity) {
        //create a shopping product from a products product
        Product product = new Product(productDto.getProductNumber(),
                productDto.getName(), productDto.getPrice(),
                productDto.getDescription(), productDto.getNumInStock());
        Optional<ShoppingCart> cartOptional = shoppingCartRepository.findById(cartId);
        if (cartOptional.isPresent() && product != null) {
            ShoppingCart cart = cartOptional.get();
            cart.addToCart(product, quantity);
            shoppingCartRepository.save(cart);
        } else if (product != null) {
            ShoppingCart cart = new ShoppingCart();
            cart.setId(cartId);
            cart.addToCart(product, quantity);
            shoppingCartRepository.save(cart);
        }
    }

    public void removeFromCart(Long cartId, ProductDTO productDTO) {
        Product product = new Product(productDTO.getProductNumber(),
                productDTO.getName(), productDTO.getPrice(),
                productDTO.getDescription(), productDTO.getNumInStock());
        Optional<ShoppingCart> cartOptional = shoppingCartRepository.findById(cartId);
        if (cartOptional.isPresent() && product != null) {
            ShoppingCart cart = cartOptional.get();
            cart.removeFromCart(product);
            shoppingCartRepository.save(cart);
        } else {
           throw new NoSuchElementException("Cart ID or Product not found. Try again");
        }
    }

    public ShoppingCartDTO getCart(Long cartId) {
        Optional<ShoppingCart> cart = shoppingCartRepository.findById(cartId);
        if (cart.isPresent())
            return ShoppingCartAdapter.getShoppingCartDTOFromShoppingCart(cart.get());
        else
            return null;
    }

}
