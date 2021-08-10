package shoppingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shoppingservice.data.ShoppingCartRepository;

@Service
public class ShoppingCartService {
    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    
}
