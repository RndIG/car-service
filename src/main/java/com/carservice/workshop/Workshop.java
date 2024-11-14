package com.carservice.workshop;

import com.carservice.entities.Order;
import com.carservice.utils.OrderStatus;
import com.carservice.entities.Employee;

import java.time.LocalDateTime;
import java.util.*;

public class Workshop {




    private Map<Integer,Order> orders;


    public Workshop(){
        this.orders = new HashMap<Integer, Order>();
    }

    public Map<Integer, Order> getOrders() {
        return orders;
    }

    public void openOrder(int orderId, int price) {
        if (!orders.containsKey(orderId)) {
            orders.put(orderId, new Order(orderId, price));
        } else {
            System.out.println("Invalid order ID/order already exists");
        }
    }

    public void assignWorker(int orderId) {
        if (orders.containsKey(orderId) && orders.get(orderId).getStatus() == OrderStatus.OPEN) {
            orders.get(orderId).addAssignee(new Employee());
        } else {
            System.out.println("There is no opened order under this order ID");
        }
    }

    public void closeOrder(int orderId) {
        if (!orders.containsKey(orderId)) {
            System.out.println("Invalid order ID");
        } else if(orders.get(orderId).getStatus() == OrderStatus.CLOSED) {
            System.out.println("Order is already closed");
        } else if (!orders.get(orderId).getAssignees().isEmpty()) {
            orders.get(orderId).setClosed(LocalDateTime.now());
            orders.get(orderId).setStatus(OrderStatus.CLOSED);
        } else {
            System.out.println("Order has no assigned workers");
        }
    }

    public void cancelOrder(int orderId) {
        if (!orders.containsKey(orderId)){
            System.out.println("Invalid order ID");
        } else if (orders.get(orderId).getStatus() == OrderStatus.OPEN) {
            orders.remove(orderId);
        } else  {
            System.out.println("Order is closed");
        }
    }

    public ArrayList<Order> sortOrders() {
        ArrayList<Order> sortedOrders = new ArrayList<>(this.orders.values());
        Collections.sort(sortedOrders);
        return sortedOrders;
    }

    public void pageOrders(int page, int pageSize) throws IndexOutOfBoundsException, IllegalArgumentException{
        if (this.orders.isEmpty()) {
            System.out.println("There is no orders");
            return;
        }

        ArrayList<Order> sortedOrders = this.sortOrders();

        int fromIndex = (page - 1) * pageSize;
        fromIndex = fromIndex >= sortedOrders.size() ? 0 : fromIndex;

        int toIndex = Math.min((fromIndex + pageSize), sortedOrders.size());

        for (Order o : sortedOrders.subList(fromIndex, toIndex)){
            System.out.println(o);
        }
    }
}
