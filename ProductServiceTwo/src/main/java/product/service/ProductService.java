package product.service;

import kafka.OrderRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.data.ProductRepository;
import product.domain.Product;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Boolean isEnoughInStock(String productId, int quantity) {
        Optional<Product> result = productRepository.findById(productId);
        if (result.isPresent()) {
            return result.get().isEnoughInStock(quantity);
        } else {
            return null;
        }
    }

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

    public void updateStock(OrderRecord orderRecord) {
        for (int i = 0; i < orderRecord.getOrderLineItems().size(); i++) {
            String prodNumber = orderRecord.getOrderLineItems().get(i).getProduct().getProductNumber();
            Optional<Product> result = productRepository.findById(prodNumber);
            if (result.isPresent()) {
                Product savedProduct = result.get();
                savedProduct.decrementStock(orderRecord.getOrderLineItems().get(i).getQuantity());
                productRepository.save(savedProduct);
            }
        }
    }
}
