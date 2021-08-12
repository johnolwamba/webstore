package shoppingcartquery.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import shoppingcartquery.domain.ShoppingCart;

@Repository
public interface ShoppingCartQueryRepository extends MongoRepository<ShoppingCart, Long>{
}
