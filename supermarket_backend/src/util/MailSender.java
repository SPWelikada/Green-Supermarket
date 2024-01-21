package util;

import entity.Order;
import entity.Order_Item;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class MailSender {

    private static void sendRegistrationSuccessEmail(String userEmail) {
        final String username = "greensupermarketb70@gmail.com"; // Your Gmail username
        final String password = "vcndfopvgdpbqlgi"; // Your Gmail password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("greensupermarketb70@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            message.setSubject("Registration Success");
            message.setText("Dear User,\n\nCongratulations! Your registration was successful.");

            Transport.send(message);
            System.out.println("Registration success email sent to: " + userEmail);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


    public static void orderPlaceSuccess(String userEmail, List<Order_Item> orderItemList) {
        final String username = "greensupermarketb70@gmail.com"; // Your Gmail username
        final String password = "vcndfopvgdpbqlgi"; // Your Gmail password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("greensupermarketb70@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            message.setSubject("Order Confirmation");

            // Email template with table format
            StringBuilder emailTemplate = new StringBuilder("Dear User,\n\nCongratulations! Your order has been confirmed.\n\n");
            emailTemplate.append(String.format("%-20s %-15s %-10s\n", "Product Name", "Quantity", "UnitPrice ($)"));
            emailTemplate.append("--------------------------------------------------\n");

            // Add your product details here dynamically
            double total = 0;
            for (Order_Item orderItem: orderItemList
                 ) {
                emailTemplate.append(String.format("%-20s %-25s %-25s\n", orderItem.getProductId(), orderItem.getQuantity(), orderItem.getPrice()));
                total = total + orderItem.getPrice() * orderItem.getQuantity();
            }


            emailTemplate.append("--------------------------------------------------\n");
            emailTemplate.append(String.format("%-35s %-10s\n", "Total  $", total));
            message.setText(emailTemplate.toString());
            Transport.send(message);
            System.out.println("Order confirmation email sent to: " + userEmail);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public static void orderDeliverSuccess(String userEmail, Order orderItemList) {
        final String username = "greensupermarketb70@gmail.com"; // Your Gmail username
        final String password = "vcndfopvgdpbqlgi"; // Your Gmail password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("greensupermarketb70@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            message.setSubject("Order Confirmation");

            // Email template with table format
            StringBuilder emailTemplate = new StringBuilder("Dear User,\n\nCongratulations! Your order has been delivered.\n\n");
            emailTemplate.append(String.format("%-50s %-70s %-70s\n", "Order_Id", "OrderDate", "customerId"));
            emailTemplate.append("-------------------------------------------------------------------------------------------\n");

            // Add your product details here dynamically

            emailTemplate.append(String.format("%-50s %-50s %-50s\n", orderItemList.getId(), orderItemList.getOrderDate(), orderItemList.getCustomerId()));

            message.setText(emailTemplate.toString());
            Transport.send(message);
            System.out.println("Order confirmation email sent to: " + userEmail);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }



    public static void orderCancelMail(String userEmail, Order orderItemList) {
        final String username = "greensupermarketb70@gmail.com"; // Your Gmail username
        final String password = "vcndfopvgdpbqlgi"; // Your Gmail password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("greensupermarketb70@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            message.setSubject("Order Confirmation");

            // Email template with table format
            StringBuilder emailTemplate = new StringBuilder("Dear User,\n\n Your order has been Canceled.\n\n");
            emailTemplate.append(String.format("%-50s %-70s %-70s\n", "Order_Id", "OrderDate", "customerId"));
            emailTemplate.append("-------------------------------------------------------------------------------------------\n");

            // Add your product details here dynamically

            emailTemplate.append(String.format("%-50s %-50s %-50s\n", orderItemList.getId(), orderItemList.getOrderDate(), orderItemList.getCustomerId()));

            message.setText(emailTemplate.toString());
            Transport.send(message);
            System.out.println("Order confirmation email sent to: " + userEmail);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
