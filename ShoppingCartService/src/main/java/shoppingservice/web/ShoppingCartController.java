package shoppingservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import shoppingservice.service.ShoppingCartService;

@RestController
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;

}
