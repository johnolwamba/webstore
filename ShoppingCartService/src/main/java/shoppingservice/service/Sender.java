package shoppingservice.service;

import kafka.ShoppingCartRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {
    @Autowired
    private KafkaTemplate<String, ShoppingCartRecord> kafkaTemplate;

    public void send(ShoppingCartRecord shoppingCartRecord) {
        kafkaTemplate.send("checkouttopic", shoppingCartRecord);
    }
}
