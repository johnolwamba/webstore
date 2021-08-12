package order.service;

import kafka.OrderRecord;
import kafka.ShoppingCartRecord;
import order.data.OrderRepository;
import order.domain.Address;
import order.domain.Contact;
import order.domain.Customer;
import order.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    Sender sender;

    public Order createOrder(ShoppingCartRecord shoppingCartRecord) {
        Order order = new Order();
        order.setId(sequenceGeneratorService.generateSequence(Order.SEQUENCE_NAME));
        Customer customer = new Customer();
        customer.setCustomerId(shoppingCartRecord.getCustomer().getCustomerId());
        customer.setFirstName(shoppingCartRecord.getCustomer().getFirstName());
        customer.setLastName(shoppingCartRecord.getCustomer().getLastName());
        customer.setContact(new Contact(shoppingCartRecord.getCustomer().getContact().getPhone(),
                shoppingCartRecord.getCustomer().getContact().getEmail()));
        customer.setAddress(new Address(shoppingCartRecord.getCustomer().getAddress().getStreet(),
                shoppingCartRecord.getCustomer().getAddress().getCity(),
                shoppingCartRecord.getCustomer().getAddress().getZip()));

        order.setCustomer(customer);
        order.setOrderLineItemsFromDTO(shoppingCartRecord.getCartlineList());
        System.out.println("Order saved: " + order);
        return orderRepository.save(order);
    }

    public Order getOrder(Long orderId) {
        Optional<Order> result = orderRepository.findById(orderId);
        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

    public Order placeOrder(Long orderId) {
        Optional<Order> result = orderRepository.findById(orderId);
        if (result.isPresent()) {
            Order order = result.get();
            System.out.println("Sending order message: "+ order);
            OrderRecord orderRecord = new OrderRecord();
            orderRecord.setId(order.getId());
            orderRecord.setCustomer(order.getCustomer());
            orderRecord.setOrderLineItems(order.getOrderLineItems());
            sender.send(orderRecord);
            System.out.println("Sending email ---> " + "\n" +
                    "Your order has been placed: \n" +
                    order.orderReceipt());
            return result.get();
        } else {
            return null;
        }
    }
}
