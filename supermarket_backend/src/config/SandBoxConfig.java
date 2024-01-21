package config;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import com.paypal.orders.Order;
import com.paypal.orders.OrdersCaptureRequest;
import com.paypal.orders.OrdersCreateRequest;
import dto.CardDetailsDTO;

import java.io.IOException;
import java.net.http.HttpResponse;

public class SandBoxConfig {

//    private static final String CLIENT_ID = "AatnkA9syODNrVxDx_EWpFHI0DQFZS5txdVGyh9DR2cgPId7tnFODs83eg09C64anAOz7Aykp-xO1Q7o";
//    private static final String CLIENT_SECRET = "EH0oR1Xw9drFqeuiA_-qtSWY7_grFSjVTobkCjjrFeaH4PIBsrHjB58Mco8iQ-tVAHMtz4OZt0IZ8Yb4";


//    public static void pay(CardDetailsDTO cardDetailsDTO,String orderId){
//
//        PayPalEnvironment environment = new PayPalEnvironment.Sandbox(CLIENT_ID, CLIENT_SECRET);
//        PayPalHttpClient client = new PayPalHttpClient(environment);
//        OrdersCaptureRequest captureOrderRequest = new OrdersCaptureRequest(orderId);
//
//    }
//
//    public static PayPalHttpClient getClient() {
//        PayPalEnvironment environment = new PayPalEnvironment.Sandbox(CLIENT_ID, CLIENT_SECRET);
//        return new PayPalHttpClient(environment);
//    }
//
//    private static Order buildOrder() {
//        // Implement logic to build an order (including item details, amounts, etc.)
//        // ...
//
//        return new Order(); // Replace this with your order object
//    }
//    public static void createOrder() {
//        PayPalHttpClient client = getClient();
//
//        Order order = buildOrder(); // Implement the logic to build an order
//        OrdersCreateRequest request = new OrdersCreateRequest().requestBody(order);
//
//        try {
//            HttpResponse<Order> response = client.execute(request);
//            if (response.statusCode() == 201) {
//                Order createdOrder = response.result();
//                String orderId = createdOrder.id();
//                System.out.println("Created Order ID: " + orderId);
//
//                // Capture the order
//                captureOrder(client, orderId);
//            } else {
//                System.out.println("Order creation failed with status code: " + response.statusCode());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private static void captureOrder(PayPalHttpClient client, String orderId) {
//        OrdersCaptureRequest request = new OrdersCaptureRequest(orderId);
//
//        try {
//            HttpResponse<Order> response = client.execute(request);
//            if (response.statusCode() == 201) {
//                Order capturedOrder = response.result();
//                System.out.println("Captured Order ID: " + capturedOrder.id());
//                // Handle successful order capture
//            } else {
//                System.out.println("Order capture failed with status code: " + response.statusCode());
//                // Handle capture failure
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


}
