package com.carservice.workshop;

import com.carservice.entities.Order;
import com.carservice.utils.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class WorkshopTest {

    private Workshop workshop;
    Map <Integer, Order> orders;

    @BeforeEach
    void loadWorkshop(){
        workshop = new Workshop();
        this.orders = workshop.getOrders();
    }

    @Test
    void openOrder() {
        workshop.openOrder(1,1000);
        assertFalse(orders.isEmpty());
    }

    @Test
    void openOrderDuplicateOrderId() {
        workshop.openOrder(2, 1200);
        workshop.openOrder(2,5000);
        assertNotEquals(5000, orders.get(2).getPrice());
        assertEquals(1200, orders.get(2).getPrice());
    }


    @Test
    void assignWorker() {
        workshop.openOrder(1,1000);
        Order testOrder = orders.get(1);
        workshop.assignWorker(1);
        workshop.assignWorker(1);
        assertEquals(2, testOrder.getAssignees().size());
    }

    @Test
    void assignWorkerNoSuchOrderId() {
        workshop.openOrder(1,1000);
        Order testOrder = orders.get(1);
        workshop.assignWorker(2);
        assertEquals(0, testOrder.getAssignees().size());
    }

    @Test
    void closeOrder() {
        workshop.openOrder(1,1000);
        Order testOrder = orders.get(1);
        assertEquals(OrderStatus.OPEN,testOrder.getStatus());
        workshop.assignWorker(1);
        workshop.closeOrder(1);
        assertEquals(OrderStatus.CLOSED,testOrder.getStatus());
    }

    @Test
    void closeOrderNoWorkers() {
        workshop.openOrder(1,1000);
        Order testOrder = orders.get(1);
        assertTrue(testOrder.getAssignees().isEmpty());
        workshop.closeOrder(1);
        assertEquals(OrderStatus.OPEN,testOrder.getStatus());
    }
}