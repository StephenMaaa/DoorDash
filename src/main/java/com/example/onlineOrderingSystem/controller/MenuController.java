package com.example.onlineOrderingSystem.controller;

import com.example.onlineOrderingSystem.entity.MenuItem;
import com.example.onlineOrderingSystem.entity.Restaurant;
import com.example.onlineOrderingSystem.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/restaurant/{restaurantID}/menu", method = RequestMethod.GET)
    @ResponseBody
    public List<MenuItem> getMenus(@PathVariable("restaurantID") int restaurantID) {
        return menuService.getAllMenuItems(restaurantID);
    }

    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    @ResponseBody
    public List<Restaurant> getRestaurants() {
        return menuService.getRestaurants();
    }
}
