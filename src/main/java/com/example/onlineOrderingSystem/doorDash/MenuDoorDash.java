package com.example.onlineOrderingSystem.doorDash;

import com.example.onlineOrderingSystem.entity.MenuItem;
import com.example.onlineOrderingSystem.entity.Restaurant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MenuDoorDash {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Restaurant> getRestaurants() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Restaurant> criteriaQuery = criteriaBuilder.createQuery(Restaurant.class);
            criteriaQuery.from(Restaurant.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<MenuItem> getAllMenuItems(int restaurantID) {
        try (Session session = sessionFactory.openSession()) {
            Restaurant restaurant = session.get(Restaurant.class, restaurantID);
            if (restaurant != null) {
                return restaurant.getMenuItemList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public MenuItem getMenuItem(int menuItemID) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(MenuItem.class, menuItemID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
