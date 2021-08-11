package shoppingservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shoppingservice.service.ProductDTO;
import shoppingservice.service.ShoppingCartDTO;
import shoppingservice.service.ShoppingCartService;

@RestController
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;

    @PostMapping(value = "/cart/{cartId}/{quantity}")
    public ResponseEntity<?> addToCart(@PathVariable Long cartId, @PathVariable int quantity, @RequestBody ProductDTO productDto) {
        shoppingCartService.addToCart(cartId, productDto, quantity);
        return new ResponseEntity<ShoppingCartDTO>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/cart/{cartId}/")
    public ResponseEntity<?> removeFromCart(@PathVariable Long cartId, @RequestBody ProductDTO productDto) {
        shoppingCartService.removeFromCart(cartId, productDto);
        return new ResponseEntity<ShoppingCartDTO>(HttpStatus.OK);
    }

    @GetMapping("/cart/{cartId}")
    public ResponseEntity<?> getCart(@PathVariable Long cartId) {
        ShoppingCartDTO cart = shoppingCartService.getCart(cartId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
