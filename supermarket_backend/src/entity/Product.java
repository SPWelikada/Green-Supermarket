package entity;

import java.math.BigDecimal;
import java.util.Arrays;


public class Product {
    private int id;
    private String name;
    private BigDecimal listPrice;
    private BigDecimal unitPrice;
    private int qty;
    private String imagePath;

    public Product() {
    }

    public Product(int id, String name, BigDecimal listPrice, BigDecimal unitPrice, int qty, String imagePath) {
        this.id = id;
        this.name = name;
        this.listPrice = listPrice;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", listPrice=" + listPrice +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}