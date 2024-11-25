package com.carservice;


import com.carservice.entities.WorkshopData;
import com.carservice.utils.ConfigurationLoader;
import com.carservice.workshop.Workshop;

import java.util.Properties;

public class Main {
    public static void main (String[] args) {
        System.out.println("start");
        Properties config = new ConfigurationLoader().getConfiguration();
        WorkshopData workshopData = new WorkshopData(config.getProperty("dataFilePath"));
        workshopData.loadData();
        Workshop autoService = new Workshop();
        autoService.setOrders(workshopData.getData());
        autoService.pageOrders(1,10);
        workshopData.saveData();
    }
}
