package com.carservice.entities;


import com.carservice.utils.OrderStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.LinkedList;

public class Order implements Comparable<Order>{


    private int id;
    private int price;
    private LocalDateTime opened;
    private LocalDateTime closed;
    private OrderStatus status;
    private LinkedList<Employee> assignees;

    public Order (int id, int price) {
        this.id = id;
        this.opened = LocalDateTime.now();
        this.price = price;
        this.status = OrderStatus.OPEN;
        this.assignees = new LinkedList<>();
    }

    public int getId() {
        return id;
    }
    public int getPrice() {
        return price;
    }
    public LocalDateTime getOpened() {
        return opened;
    }
    public LocalDateTime getClosed() {
        return closed;
    }
    public void setClosed(LocalDateTime closed) {
        this.closed = closed;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    public LinkedList<Employee> getAssignees() {
        return assignees;
    }
    public void setAssignees(LinkedList<Employee> assignees) {
        this.assignees = assignees;
    }



    public void addAssignee(Employee employee) {
        this.assignees.add(employee);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        StringBuilder str = new StringBuilder();
        str.append("[order ID :").append(id)
                .append("][price :").append(price)
                .append("][opened :").append(opened.format(formatter))
                .append("][status :").append(status)
                .append("][closed :").append(closed != null ? closed.format(formatter) : "N/A")
                .append("]");

        return str.toString();
    }

    @Override
    public int compareTo(Order o) {
        return Comparator.comparing(Order :: getId)
                .thenComparing(Order :: getPrice)
                .thenComparing(Order :: getOpened)
                .thenComparing(Order :: getClosed)
                .thenComparing(Order :: getStatus)
                .compare(this, o);
    }
}
