package serviceImpl;


import entity.Order_Item;

import repoImpl.OrderItemRepositoryImpl;


import java.sql.SQLException;
import java.util.List;


public class OrderItemServiceImpl  {
    private OrderItemRepositoryImpl orderItemRepository = new OrderItemRepositoryImpl();



    public void createOrderItem(List<Order_Item> orderItem) throws SQLException {
        orderItemRepository.createOrderItem(orderItem);
    }


    public Order_Item getOrderItemById(int orderItemId) throws SQLException {
        return (Order_Item) orderItemRepository.getOrderItemById(orderItemId);
    }


    public List<Order_Item> getAllOrderItems() throws SQLException {
       return orderItemRepository.getAllOrderItems();
    }


    public void updateOrderItem(Order_Item orderItem) throws SQLException {
        orderItemRepository.updateOrderItem(orderItem);
    }


    public void deleteOrderItem(int orderItemId) throws SQLException {
        orderItemRepository.deleteOrderItem(orderItemId);
    }
}
