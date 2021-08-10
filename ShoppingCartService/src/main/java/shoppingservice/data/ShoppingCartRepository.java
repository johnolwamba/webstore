package shoppingservice.data;

import org.springframework.stereotype.Repository;
import shoppingservice.domain.ShoppingCart;

@Repository
public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, Long>{
}
