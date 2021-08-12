package order.domain;

import kafka.CartLineDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;

@Document(collection = "orders")
public class Order {

    @Transient
    public static final String SEQUENCE_NAME = "orders_sequence";

    @Id
    private Long id;

    private Customer customer;
    ArrayList<OrderLineItem> orderLineItems = new ArrayList<OrderLineItem>();

    public Order() {
    }

    public Order(Long id, Customer customer, ArrayList<OrderLineItem> orderLineItems) {
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

    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (OrderLineItem orderLineItem : orderLineItems) {
            totalPrice = totalPrice + (orderLineItem.getProduct().getPrice() * orderLineItem.getQuantity());
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + id +
                ", customer=" + customer +
                ", orderLineItems=" + orderLineItems +
                '}';
    }

    public String orderReceipt() {
        return "Order Number: " + this.id + "\n" +
                "Total Amount: " + this.getTotalPrice() + "\n" +
                "Ordered Products: " + this.orderLineItems + "\n" +
                "Order Date: " + new Date();
    }

    public void setOrderLineItemsFromDTO(ArrayList<CartLineDTO> cartlineList) {
        ArrayList<OrderLineItem> newLines = new ArrayList<>();
        for (int i = 0; i < cartlineList.size(); i++) {
            newLines.add(new OrderLineItem(cartlineList.get(i).getQuantity(),
                    new Product(cartlineList.get(i).getProduct().getProductNumber(),
                            cartlineList.get(i).getProduct().getName(),
                            cartlineList.get(i).getProduct().getPrice(),
                            cartlineList.get(i).getProduct().getDescription(),
                            cartlineList.get(i).getProduct().getNumInStock())));
        }
        this.setOrderLineItems(newLines);
    }
}
