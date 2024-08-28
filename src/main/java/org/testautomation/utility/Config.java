package org.testautomation.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testautomation.utility.reading.ReadFromFile;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private Config() {
    }

    private static final Logger log = LoggerFactory.getLogger(Config.class);
    private static final String DEFAULT_PROPERTIES = "config/test.properties";
    static Properties properties;

    public static void initialize() {
        properties = loadProperties();

        for (String key : properties.stringPropertyNames()) {
            if (System.getProperties().containsKey(key)) {
                properties.setProperty(key, System.getProperty(key));
            }
        }

        log.info("Test properties");
        for (String key : properties.stringPropertyNames()) {
            if (log.isInfoEnabled()) {
                log.info("{} = {}", key, properties.getProperty(key));
            }
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    private static Properties loadProperties() {
        properties = new Properties();
        try (InputStream inputStream = ReadFromFile.getResource(DEFAULT_PROPERTIES)) {
            properties.load(inputStream);
        } catch (Exception e) {
            log.error("Unable to read config properties from: {}", DEFAULT_PROPERTIES, e);
        }
        return properties;
    }
}
