package com.carservice.entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WorkshopDataTest {

    private static final String TEST_FILEPATH = "src/test/resources/data.ser";
    private WorkshopData workshopData;
    private Map<Integer, Order> testData;

    @BeforeEach
    void setUp() {
        testData = new HashMap<>();
        testData.put(1, new Order(1,1111));
        testData.put(2, new Order(2,2222));
        workshopData = new WorkshopData(TEST_FILEPATH);


    }

    @AfterEach
    void clear() {
        File dataFile = new File(TEST_FILEPATH);
        if(dataFile.exists()) {
            dataFile.delete();
        }
    }

    @Test
    void saveDataTest() {
        workshopData.setData(testData);
        workshopData.saveData();
        File dataFile = new File(TEST_FILEPATH);
        assertTrue(dataFile.exists());
        workshopData.loadData();
        assertEquals(testData, workshopData.getData());
    }

    @Test
    void loadDataNoFileTest() {
        //File datataFile = new File(TEST_FILEPATH);
        workshopData.loadData();
        assertNotNull(workshopData.getData());
        assertTrue(workshopData.getData().isEmpty());
    }
}