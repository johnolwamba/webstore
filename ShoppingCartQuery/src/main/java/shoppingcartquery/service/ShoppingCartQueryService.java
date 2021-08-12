package shoppingcartquery.service;

import kafka.ProductDTO;
import kafka.QueryUpdateRecord;
import kafka.ShoppingCartRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shoppingcartquery.data.ShoppingCartQueryRepository;
import shoppingcartquery.domain.CartLine;
import shoppingcartquery.domain.Customer;
import shoppingcartquery.domain.Product;
import shoppingcartquery.domain.ShoppingCart;
import shoppingcartquery.domain.Contact;
import shoppingcartquery.domain.Address;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ShoppingCartQueryService {
    @Autowired
    ShoppingCartQueryRepository shoppingCartRepository;

    public ShoppingCartDTO getCart(Long cartId) {
        Optional<ShoppingCart> cart = shoppingCartRepository.findById(cartId);
        if (cart.isPresent())
            return ShoppingCartAdapter.getShoppingCartDTOFromShoppingCart(cart.get());
        else
            return null;
    }

    public void createQuery(QueryUpdateRecord queryUpdateRecord) {
        if (queryUpdateRecord.getDelete()) {
            deleteQuery(queryUpdateRecord.getShoppingCartRecord());
        } else {
            createNew(queryUpdateRecord.getShoppingCartRecord());
        }
    }

    public void createNew(ShoppingCartRecord shoppingCartRecord) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(shoppingCartRecord.getId());

        ArrayList<CartLine> cartLines = new ArrayList<>();
        for (int i = 0; i < shoppingCartRecord.getCartlineList().size(); i++) {
            ProductDTO prodDTO = shoppingCartRecord.getCartlineList().get(i).getProduct();
            Product prod = new Product(prodDTO.getProductNumber(), prodDTO.getName(),
                    prodDTO.getPrice(), prodDTO.getDescription(),
                    prodDTO.getNumInStock());
            cartLines.add(new CartLine(shoppingCartRecord.getCartlineList().get(i).getQuantity(), prod));
        }
        Customer customer = new Customer();
        customer.setCustomerId(shoppingCartRecord.getCustomer().getCustomerId());
        customer.setFirstName(shoppingCartRecord.getCustomer().getFirstName());
        customer.setLastName(shoppingCartRecord.getCustomer().getLastName());
        customer.setContact(new Contact(shoppingCartRecord.getCustomer().getContact().getPhone(),
                shoppingCartRecord.getCustomer().getContact().getEmail()));
        customer.setAddress(new Address(shoppingCartRecord.getCustomer().getAddress().getStreet(),
                shoppingCartRecord.getCustomer().getAddress().getCity(),
                shoppingCartRecord.getCustomer().getAddress().getZip()));
        shoppingCart.setCartlineList(cartLines);
        shoppingCart.setCustomer(customer);
        shoppingCartRepository.save(shoppingCart);
    }

    public void deleteQuery(ShoppingCartRecord shoppingCartRecord) {
        shoppingCartRepository.deleteById(shoppingCartRecord.getId());
    }
}
