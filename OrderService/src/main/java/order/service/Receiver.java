package order.service;


import kafka.ShoppingCartRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Receiver {

    @Autowired
    Sender sender;

    @Autowired
    OrderService orderService;

    @KafkaListener(topics = "checkouttopic")
    public void receive(@Payload ShoppingCartRecord shoppingCartRecord,
                        @Headers MessageHeaders headers) {
        System.out.println("received message=" + shoppingCartRecord);

        orderService.createOrder(shoppingCartRecord);
    }
}