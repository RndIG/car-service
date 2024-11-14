package com.carservice;


import com.carservice.workshop.Workshop;

public class Main {
    public static void main (String[] args) {
        System.out.println("start");
        Workshop autoService = new Workshop();

        autoService.openOrder(1, 1000);
        autoService.openOrder(2, 1200);
        autoService.openOrder(5,3000);
        autoService.openOrder(4,3000);

        autoService.pageOrders(1,10);
    }
}
