package com.example.e_commerce.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.e_commerce.Entity.OrderEntity;
import com.example.e_commerce.Entity.OrderItem;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	 @Autowired
	    private JavaMailSender mailSender;

	    public void sendOtpEmail(String toEmail, String otp) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(toEmail);
	        message.setSubject("Reset Your Password - OTP");
	        message.setText("Your OTP for password reset is: " + otp);
	        mailSender.send(message);
	    }
	    
//	    public void sendOrderConfirmation(String toEmail, OrderEntity order) {
//	        String subject = "Order Confirmation - Order #" + order.getId();
////	        String message = "Hello " + order.getUser().getUsername() + ",\n\n" +
////	                "Thank you for your purchase! Your order has been placed successfully.\n" +
////	                "Order ID: " + order.getId() + "\n" +
////	                "Total Amount: ₹" + order.getTotalAmount() + "\n" +
////	                "Status: " + order.getStatus() + "\n\n" +
////	                "We will notify you once your items are shipped.\n\n" +
////	                "Thanks,\nE-Commerce Team";
//	        
//	        String message = "<html>"
//	                + "<body>"
//	                + "<p>Hello <strong>" + order.getUser().getUsername() + "</strong>,</p>"
//	                + "<p>Thank you for your purchase! Your order has been placed successfully.</p>"
//	                + "<table style='border-collapse: collapse;'>"
//	                + "<tr><td><strong>Order ID:</strong></td><td>" + order.getId() + "</td></tr>"
//	                + "<tr><td><strong>Total Amount:</strong></td><td>₹" + order.getTotalAmount() + "</td></tr>"
//	                + "<tr><td><strong>Status:</strong></td><td>" + order.getStatus() + "</td></tr>"
//	                + "</table>"
//	                + "<p>We will notify you once your items are shipped.</p>"
//	                + "<p>Thanks,<br/>E-Commerce Team</p>"
//	                + "</body>"
//	                + "</html>";
//
//	        sendEmail(toEmail, subject, message);
//	    }
	    
	    //Order Confirmation mail
	    public void sendOrderConfirmation(String toEmail, OrderEntity order) {
	        try {
	            MimeMessage message = mailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

	            helper.setTo(toEmail);
	            helper.setSubject("Your Order Confirmation - Order #" + order.getId());

	            String htmlContent = "<html>"
	                    + "<body>"
	                    + "<p>Hello <strong>" + order.getUser().getUsername() + "</strong>,</p>"
	                    + "<p>Thank you for your purchase! Your order has been placed successfully.</p>"
	                    + "<table style='border-collapse: collapse;'>"
	                    + "<tr><td><strong>Order ID:</strong></td><td>" + order.getId() + "</td></tr>"
	                    + "<tr><td><strong>Total Amount:</strong></td><td>₹" + order.getTotalAmount() + "</td></tr>"
	                    + "<tr><td><strong>Status:</strong></td><td>" + order.getStatus() + "</td></tr>"
	                    + "</table>"
	                    + "<p>We will notify you once your items are shipped.</p>"
	                    + "<p>Thanks,<br/> SAELRI MART</p>"
	                    + "</body>"
	                    + "</html>";

	            helper.setText(htmlContent, true); // important: 'true' means HTML content

	            mailSender.send(message);
	            System.out.println("Order confirmation email sent to " + toEmail);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    
	    //payment mail
	    public void sendPaymentSuccessEmail(String toEmail, OrderEntity order) {
	        try {
	            MimeMessage message = mailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

	            helper.setTo(toEmail);
	            helper.setSubject("Payment Received - Order #" + order.getId());

	            String htmlContent = "<html><body>"
	                    + "<h2>Payment Successful!</h2>"
	                    + "<p>Hello <strong>" + order.getUser().getUsername() + "</strong>,</p>"
	                    + "<p>Your payment for Order ID <strong>" + order.getId() + "</strong> has been successfully processed.</p>"
	                    + "<p><strong>Total Amount:</strong> ₹" + order.getTotalAmount() + "</p>"
	                    + "<p>Status: " + order.getStatus() + "</p>"
	                    + "<p>Thanks for SAELRI MART shopping with us!</p>"
	                    + "</body></html>";

	            helper.setText(htmlContent, true);
	            mailSender.send(message);
	            System.out.println("✅ Payment confirmation email sent to: " + toEmail);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	   
	        // Method to send email when a single item is refunded
	        public void sendItemRefundEmail(String toEmail, OrderItem orderItem, OrderEntity order) {
	            try {
	                MimeMessage message = mailSender.createMimeMessage();
	                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

	                helper.setTo(toEmail);
	                helper.setSubject("Refund Confirmation for Order #" + order.getId());

	                // HTML content
	                String htmlContent = "<html>"
	                        + "<body>"
	                        + "<p>Hello <strong>" + order.getUser().getUsername() + "</strong>,</p>"
	                        + "<p>The following item from your order has been refunded:</p>"
	                        + "<table style='border-collapse: collapse; border: 1px solid black;'>"
	                        + "<tr><td><strong>Product:</strong></td><td>" + orderItem.getProduct().getName() + "</td></tr>"
	                        + "<tr><td><strong>Quantity:</strong></td><td>" + orderItem.getQuantity() + "</td></tr>"
	                        + "<tr><td><strong>Price per item:</strong></td><td>₹" + orderItem.getPriceAtPurchase() + "</td></tr>"
	                        + "<tr><td><strong>Refunded Amount:</strong></td><td>₹" + (orderItem.getQuantity() * orderItem.getPriceAtPurchase()) + "</td></tr>"
	                        + "</table>"
	                        + "<p>Your updated order total is: <strong>₹" + order.getTotalAmount() + "</strong></p>"
	                        + "<p>Thank you for shopping with us.</p>"
	                        + "<p>Regards,<br/>E-Commerce Team</p>"
	                        + "</body>"
	                        + "</html>";

	                helper.setText(htmlContent, true); // true = HTML

	                mailSender.send(message);
	                System.out.println("Refund email sent to: " + toEmail);

	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    



//	    public void sendEmail(String toEmail, String subject, String message) {
//	        SimpleMailMessage mailMessage = new SimpleMailMessage();
//	        mailMessage.setFrom("your_email@gmail.com");
//	        mailMessage.setTo(toEmail);
//	        mailMessage.setSubject(subject);
//	        mailMessage.setText(message);
//
//	        mailSender.send(mailMessage);
//	        System.out.println("✅ Email sent to: " + toEmail);
//	    }
	}

