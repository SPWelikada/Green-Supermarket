package entity;

import entity.Enum.Status;


public class ShoppingCart {
    private int id;

    private int customer_id;

    private Status status;


    public ShoppingCart() {
    }

    public ShoppingCart(int id, int customer_id, Status status) {
        this.id = id;
        this.customer_id = customer_id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", customer_id=" + customer_id +
                ", status=" + status +
                '}';
    }
}

