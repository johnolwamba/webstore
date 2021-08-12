package order.service;


import kafka.Product;
import kafka.ProductDTO;

public class ProductAdapter {
    public static Product getProductFromProductDTO(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductNumber(productDTO.getProductNumber());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setNumInStock(productDTO.getNumInStock());
        return product;
    }

    public static ProductDTO getProductDTOFromProduct(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductNumber(product.getProductNumber());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setNumInStock(product.getNumInStock());
        return productDTO;
    }
}