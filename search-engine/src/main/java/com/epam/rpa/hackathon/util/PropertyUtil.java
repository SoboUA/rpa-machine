package com.epam.rpa.hackathon.util;

import com.epam.rpa.hackathon.property.Property;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

    private static Properties properties;

    public static String getProperty(Property property) {
        return getProperties().getProperty(property.toString(), null);
    }

    public static Properties getProperties() {
        return properties;
    }

    static {
        if (properties == null) {
            properties = new Properties();
            String propsPath = "src/main/resources/application.properties";
            try (InputStream is = new FileInputStream(propsPath)) {
                properties.load(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
