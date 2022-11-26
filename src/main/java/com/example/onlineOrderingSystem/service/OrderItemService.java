package com.example.onlineOrderingSystem.service;

import com.example.onlineOrderingSystem.doorDash.OrderItemDoorDash;
import com.example.onlineOrderingSystem.entity.Customer;
import com.example.onlineOrderingSystem.entity.MenuItem;
import com.example.onlineOrderingSystem.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private OrderItemDoorDash orderItemDoorDash;

    public void saveOrderItem(int menuItemID) {
        final OrderItem orderItem = new OrderItem();
        final MenuItem menuItem = menuService.getMenuItem(menuItemID);

        // authentication and authorization
        Authentication loggedUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedUser.getName();
        Customer customer = customerService.getCustomer(username);

        // save
        orderItem.setMenuItem(menuItem);
        orderItem.setCart(customer.getCart());
        orderItem.setQuantity(1);
        orderItem.setPrice(menuItem.getPrice());
        orderItemDoorDash.save(orderItem);
    }
}
