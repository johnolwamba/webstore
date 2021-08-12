package order.service;

import kafka.OrderRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {
    @Autowired
    private KafkaTemplate<String, OrderRecord> kafkaTemplate;

    public void send(OrderRecord orderRecord) {
        kafkaTemplate.send("ordertopic", orderRecord);
    }
}
