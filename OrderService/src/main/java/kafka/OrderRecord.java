package kafka;

import order.domain.Customer;
import order.domain.OrderLineItem;
import java.util.ArrayList;

public class OrderRecord {
    private Long id;

    private Customer customer;
    ArrayList<OrderLineItem> orderLineItems = new ArrayList<OrderLineItem>();

    public OrderRecord() {
    }

    public OrderRecord(Long id, Customer customer, ArrayList<OrderLineItem> orderLineItems) {
        this.id = id;
        this.customer = customer;
        this.orderLineItems = orderLineItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + id +
                ", customer=" + customer +
                ", orderLineItems=" + orderLineItems +
                '}';
    }
}
