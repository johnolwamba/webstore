package product.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import product.service.ProductDTO;
import product.service.ProductService;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/product")
    public ProductDTO addCustomer(@RequestBody ProductDTO productDTO) {
        return productService.createProduct(productDTO);
    }

    @GetMapping("/product/{productId}")
    public ProductDTO getCustomer(@PathVariable("productId") String productId) {
        return productService.getProduct(productId);
    }

    @PutMapping("/product/{productId}")
    public ProductDTO updateProduct(@PathVariable("productId") String productId,
                                    @RequestBody ProductDTO productDTO) {
        return productService.updateProduct(productDTO, productId);
    }

    @DeleteMapping("/product/{productId}")
    public void deleteProduct(@PathVariable("productId") String productId) {
        productService.deleteProduct(productId);
    }
}
