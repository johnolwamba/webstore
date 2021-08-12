package order.web;

import order.domain.Order;
import order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable Long orderId) {
        Order order = orderService.getOrder(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/order/{orderId}")
    public ResponseEntity<?> placeOrder(@PathVariable Long orderId) {
        Order order = orderService.placeOrder(orderId);
        if (order == null) {
            return new ResponseEntity<>("Order placement failed", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
    }

}
