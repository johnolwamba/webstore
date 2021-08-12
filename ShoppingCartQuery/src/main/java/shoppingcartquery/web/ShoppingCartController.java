package shoppingcartquery.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shoppingcartquery.service.ShoppingCartDTO;
import shoppingcartquery.service.ShoppingCartQueryService;

@RestController
public class ShoppingCartController {
    @Autowired
    ShoppingCartQueryService shoppingCartService;

    @GetMapping("/cart/{cartId}")
    public ResponseEntity<?> getCart(@PathVariable Long cartId) {
        ShoppingCartDTO cart = shoppingCartService.getCart(cartId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
