package shoppingcartquery.service;


import kafka.QueryUpdateRecord;
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
    ShoppingCartQueryService shoppingCartQueryService;

    @KafkaListener(topics = "updatequery")
    public void receive(@Payload QueryUpdateRecord queryUpdateRecord,
                        @Headers MessageHeaders headers) {
        System.out.println("received message=" + queryUpdateRecord);

        shoppingCartQueryService.createQuery(queryUpdateRecord);
    }
}