package order.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document
public class Order {
    @Id
    private Long orderId;

    private Customer customer;
    ArrayList<OrderLineItem> orderLineItems = new ArrayList<OrderLineItem>();

    public Order() {
    }

    public Order(Long orderId, Customer customer, ArrayList<OrderLineItem> orderLineItems) {
        this.orderId = orderId;
        this.customer = customer;
        this.orderLineItems = orderLineItems;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<OrderLineItem> getOrderLineItems() {
        return orderLineItems;
    }

    public void setOrderLineItems(ArrayList<OrderLineItem> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }

    public double getTotalPrice(){
        double totalPrice = 0.0;
        for (OrderLineItem orderLineItem : orderLineItems) {
            totalPrice=totalPrice+(orderLineItem.getProduct().getPrice() * orderLineItem.getQuantity());
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customer=" + customer +
                ", orderLineItems=" + orderLineItems +
                '}';
    }
}
