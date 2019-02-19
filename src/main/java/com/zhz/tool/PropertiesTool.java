package com.zhz.tool;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author zhz
 * @description
 * @time 2019/2/19 15:40
 */
public class PropertiesTool {
    private static final String PROPERTIES_NAME = "config.properties";
    private static Properties properties;

    static {
        properties = new Properties();
        try {
            InputStream in = PropertiesTool.class.getClassLoader().getResourceAsStream(PROPERTIES_NAME);
            properties.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getPropertiesName(String key) {
        return properties.getProperty(key);
    }
}