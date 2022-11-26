package com.example.onlineOrderingSystem.service;

import com.example.onlineOrderingSystem.doorDash.CustomerDoorDash;
import com.example.onlineOrderingSystem.entity.Cart;
import com.example.onlineOrderingSystem.entity.Customer;
import org.springframework.beans.CachedIntrospectionResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private CustomerDoorDash customerDoorDash;

    @Autowired
    public CustomerService(CustomerDoorDash customerDoorDash) {
        this.customerDoorDash = customerDoorDash;
    }

    public Customer getCustomer(String email) {
        return customerDoorDash.getCustomer(email);
    }

    public void signUp(Customer customer) {
        Cart cart = new Cart();
        customer.setCart(cart);
        customer.setEnabled(true);
        customerDoorDash.signUp(customer);
    }
}
