package product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.data.ProductRepository;
import product.domain.Product;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = ProductAdapter.getProductFromProductDTO(productDTO);
        productRepository.save(product);
        return productDTO;
    }

    public ProductDTO getProduct(String productId) {
        Optional<Product> result = productRepository.findById(productId);
        if (result.isPresent()) {
            return ProductAdapter.getProductDTOFromProduct(result.get());
        } else {
            return null;
        }
    }

    public ProductDTO updateProduct(ProductDTO customerDTO, String productId) {
        Optional<Product> result = productRepository.findById(productId);
        if (result.isPresent()) {
            Product savedProduct = result.get();
            Product updatedProduct = ProductAdapter.getProductFromProductDTO(customerDTO);
            Product product = savedProduct.update(updatedProduct);
            productRepository.save(product);
            return ProductAdapter.getProductDTOFromProduct(product);
        } else {
            return null;
        }
    }

    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }

}
