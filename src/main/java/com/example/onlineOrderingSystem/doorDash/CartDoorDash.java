package com.example.onlineOrderingSystem.doorDash;

import com.example.onlineOrderingSystem.entity.Cart;
import com.example.onlineOrderingSystem.entity.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartDoorDash {
    @Autowired
    private SessionFactory sessionFactory;

    public void removeCartItem(int orderItemID) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            OrderItem orderItem = session.get(OrderItem.class, orderItemID);
            Cart cart = orderItem.getCart();
            cart.getOrderItemList().remove(orderItem);

            session.beginTransaction();
            session.delete(orderItem);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void removeAll(Cart cart) {
        for (OrderItem orderItem : cart.getOrderItemList()) {
            removeCartItem(orderItem.getId());
        }
    }
}
