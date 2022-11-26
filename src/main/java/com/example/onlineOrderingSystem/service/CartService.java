package com.example.onlineOrderingSystem.service;

import com.example.onlineOrderingSystem.doorDash.CartDoorDash;
import com.example.onlineOrderingSystem.entity.Cart;
import com.example.onlineOrderingSystem.entity.Customer;
import com.example.onlineOrderingSystem.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartDoorDash cartDoorDash;
    @Autowired
    private CustomerService customerService;

    public Cart getCart() {
        // authentication and authorization
        Authentication loggedUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedUser.getName();
        Customer customer = customerService.getCustomer(username);

        // update cart
        if (customer != null) {
            Cart cart = customer.getCart();
            double total = 0;

            // calculate total price
            for (OrderItem orderItem : cart.getOrderItemList()) {
                total += orderItem.getPrice() * orderItem.getQuantity();
            }
            cart.setTotal(total);
            return cart;
        }
        return new Cart();
    }

    public void cleanCart() {
        // authentication and authorization
        Authentication loggedUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedUser.getName();
        Customer customer = customerService.getCustomer(username);

        if (customer != null) {
            cartDoorDash.removeAll(customer.getCart());
        }
    }
}
