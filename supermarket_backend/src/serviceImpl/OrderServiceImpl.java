package serviceImpl;

import entity.Order;

import repoImpl.OrderRepositoryImpl;

import java.util.List;


public class OrderServiceImpl  {

    private OrderRepositoryImpl orderRepository = new OrderRepositoryImpl();

    public void createOrder(Order order) throws Exception {
        orderRepository.createOrder(order);

    }

    public void DeleteOrder(String id) {
        orderRepository.DeleteOrder(id);
    }


    public void UpdateOrder(Order order) throws Exception {
    orderRepository.UpdateOrder(order);
    }

    public Order SerchOrderById(String id) {
        return orderRepository.SerchOrderById(id);

    }

    public List<Order> getAllOrder() throws Exception {
        return orderRepository.getAllOrder();
    }

}
