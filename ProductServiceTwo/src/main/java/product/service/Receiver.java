package product.service;

import kafka.OrderRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Receiver {

    @Autowired
    ProductService productService;

    @KafkaListener(topics = "ordertopic")
    public void receive(@Payload OrderRecord orderRecord,
                        @Headers MessageHeaders headers) {
        System.out.println("received message=" + orderRecord);

        productService.updateStock(orderRecord);
    }
}