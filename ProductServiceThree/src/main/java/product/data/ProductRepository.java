package product.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import product.domain.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
