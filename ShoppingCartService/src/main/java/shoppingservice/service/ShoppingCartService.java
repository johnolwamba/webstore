package shoppingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shoppingservice.data.ShoppingCartRepository;
import shoppingservice.domain.Customer;
import shoppingservice.domain.Product;
import shoppingservice.domain.ShoppingCart;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ShoppingCartService {
    @Autowired
    ShoppingCartRepository shoppingCartRepository;

//    @Autowired
//    QueryUpdateSender queryUpdateSender;

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

    public ShoppingCartDTO createCart(Long cartId) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(cartId);
        ShoppingCart shoppingCart1 = shoppingCartRepository.save(shoppingCart);
        return ShoppingCartAdapter.getShoppingCartDTOFromShoppingCart(shoppingCart1);
    }

    public ShoppingCartDTO checkoutCart(Long cartId, CustomerDTO customerDTO) {
        Optional<ShoppingCart> cartOptional = shoppingCartRepository.findById(cartId);
        if (cartOptional.isPresent()) {
            ShoppingCart cart = cartOptional.get();
            Customer customer = CustomerAdapter.getCustomerFromCustomerDTO(customerDTO);
            cart.setCustomer(customer);
            ShoppingCart savedCart = shoppingCartRepository.save(cart);
            return ShoppingCartAdapter.getShoppingCartDTOFromShoppingCart(savedCart);
        } else {
            throw new NoSuchElementException("Cart ID not found. Try again");
        }
    }
}
