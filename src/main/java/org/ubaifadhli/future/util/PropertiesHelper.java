package org.ubaifadhli.future.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class PropertiesHelper {
    private static final String PROPERTIES_NAME = "application.properties";

    public static List<String> getPropertyAsList(String propertyName) {
        return Arrays.asList(getProperty(propertyName).split(", "));
    }

    public static String getProperty(String propertyName) {
        Properties properties = new Properties();

        try {
            InputStream inputStream = new FileInputStream(DirectoryConstant.BASE_TEST_RESOURCES + PROPERTIES_NAME);

            properties.load(new BufferedInputStream(inputStream));
            return properties.getProperty(propertyName);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get property from ApplicationProperties.");
        }
    }

}
