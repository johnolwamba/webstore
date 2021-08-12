package product.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.service.ProductDTO;
import product.service.ProductService;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProductDTO = productService.createProduct(productDTO);
        return new ResponseEntity<>(createdProductDTO, HttpStatus.OK);
    }

    @GetMapping("/product/{productId}/{quantity}")
    public ResponseEntity<?> isEnoughInStock(@PathVariable("productId") String productId,
                                             @PathVariable("quantity") Integer quantity) {
        Boolean isEnough = productService.isEnoughInStock(productId, quantity);
        if (isEnough == null || Boolean.FALSE.equals(isEnough)) {
            return new ResponseEntity<>("Not Enough In Stock", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable("productId") String productId) {
        ProductDTO createdProductDTO = productService.getProduct(productId);
        return new ResponseEntity<>(createdProductDTO, HttpStatus.OK);
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable("productId") String productId,
                                           @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProductDTO = productService.updateProduct(productDTO, productId);
        return new ResponseEntity<>(updatedProductDTO, HttpStatus.OK);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable("productId") String productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
