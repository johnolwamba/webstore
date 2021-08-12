package shoppingservice.web;

import kafka.ShoppingCartRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shoppingservice.service.*;

@RestController
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    ProductFeignClient productFeignClient;

    @Autowired
    Sender sender;

    @PostMapping("cart/{cartId}")
    public ResponseEntity<?> createCart(@PathVariable Long cartId) {
        ShoppingCartDTO shoppingCartDTO = shoppingCartService.createCart(cartId);
        return new ResponseEntity<>(shoppingCartDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/cart/{cartId}/{quantity}")
    public ResponseEntity<?> addToCart(@PathVariable Long cartId, @PathVariable int quantity,
                                       @RequestBody ProductDTO productDto) {
        ResponseEntity responseEntity = productFeignClient.isEnoughInStock(productDto.getProductNumber(), quantity);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            shoppingCartService.addToCart(cartId, productDto, quantity);
            return new ResponseEntity<>("Added to cart Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/cart/checkout/{cartId}")
    public ResponseEntity<?> checkoutCart(@PathVariable Long cartId,
                                          @RequestBody CustomerDTO customerDTO) {
        ShoppingCartDTO shoppingCartDTO = shoppingCartService.checkoutCart(cartId, customerDTO);
        if (shoppingCartDTO.getCartlineList().isEmpty()) {
            //please add items to cart before checking out
            return new ResponseEntity<>("Please add items to cart before checkout", HttpStatus.NO_CONTENT);
        } else if (shoppingCartDTO.getCustomer() == null) {
            return new ResponseEntity<>("Failed to attach customer.Please attach before checkout", HttpStatus.NO_CONTENT);
        } else {
            ShoppingCartRecord record = new ShoppingCartRecord();
            record.setId(shoppingCartDTO.getId());
            record.setCustomer(shoppingCartDTO.getCustomer());
            record.setCartlineList(shoppingCartDTO.getCartlineList());
            System.out.println("Sending out record " + record);
            sender.send(record);
            return new ResponseEntity<>("Checkout successful", HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/cart/{cartId}/")
    public ResponseEntity<?> removeFromCart(@PathVariable Long cartId, @RequestBody ProductDTO productDto) {
        shoppingCartService.removeFromCart(cartId, productDto);
        return new ResponseEntity<>("Removed from cart Successfully", HttpStatus.OK);
    }

    @FeignClient(name = "ProductService")
    interface ProductFeignClient {
        @RequestMapping("/product/{productId}/{quantity}")
        public ResponseEntity<?> isEnoughInStock(@PathVariable("productId") String productId,
                                                 @PathVariable("quantity") Integer quantity);
    }
}
