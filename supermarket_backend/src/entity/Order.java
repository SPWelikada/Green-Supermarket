package entity;

import entity.Enum.OrderStatus;

import java.security.Timestamp;
import java.util.Date;


public class Order {
    private String id;
    private int customerId;
    private Date orderDate;
    private OrderStatus status;

    public Order(String id, int customerId, java.sql.Timestamp orderDate, Object status) {
    }

    public Order(String id, int customerId, Date orderDate, OrderStatus status) {
        this.setId(id);
        this.setCustomerId(customerId);
        this.setOrderDate(orderDate);
        this.setStatus(status);
    }

    public Order() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", orderDate=" + orderDate +
                ", status=" + status +
                '}';
    }
}
