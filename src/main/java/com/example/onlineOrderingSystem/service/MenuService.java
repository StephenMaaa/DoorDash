package com.example.onlineOrderingSystem.service;

import com.example.onlineOrderingSystem.doorDash.MenuDoorDash;
import com.example.onlineOrderingSystem.entity.MenuItem;
import com.example.onlineOrderingSystem.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    private MenuDoorDash menuDoorDash;

    public List<Restaurant> getRestaurants() {
        return menuDoorDash.getRestaurants();
    }

    public List<MenuItem> getAllMenuItems(int restaurantID) {
        return menuDoorDash.getAllMenuItems(restaurantID);
    }

    public MenuItem getMenuItem(int menuItemID) {
        return menuDoorDash.getMenuItem(menuItemID);
    }
}
