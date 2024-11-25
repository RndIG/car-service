package com.carservice.entities;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class WorkshopData implements Serializable {
    private Map<Integer,Order> data;
    private final String dataFilePath;

    public WorkshopData(String dataFilePath) {
        this.dataFilePath = dataFilePath;
    }
    public Map<Integer, Order> getData() {
        return data;
    }

    public void setData(Map<Integer, Order> data) {
        this.data = data;
    }

    public void saveData() {
        File dataFile = new File(dataFilePath);
        try (FileOutputStream outputStream = new FileOutputStream(dataFile);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(this.data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadData() {
        File dataFile = new File(dataFilePath);
        if(!dataFile.exists()) {
            this.data = new HashMap<>();
        } else {
            try (FileInputStream inputStream = new FileInputStream(dataFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)){
                this.data = (HashMap<Integer, Order>) objectInputStream.readObject();
            } catch (IOException | ClassCastException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
