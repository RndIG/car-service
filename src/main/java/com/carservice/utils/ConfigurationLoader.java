package com.carservice.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationLoader {
    private Properties configuration;

    public ConfigurationLoader() {
        try (FileInputStream configurationInput = new FileInputStream("src/main/resources/configuration.xml")) {
            Properties output = new Properties();
            output.loadFromXML(configurationInput );
            this.configuration = output;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Properties getConfiguration() {
        return configuration;
    }
}
