package com.bjen.dmscli;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigReader {
    Map<String, String> ConfigReader() {
        Map<String, String> configMap = new HashMap<>();
        Properties config = new Properties();
        String configFile = "src/config.properties";

        try(FileInputStream fis = new FileInputStream(configFile)){
            config.load(fis);
            for (Map.Entry<Object, Object> prop : config.entrySet()){
                configMap.put(prop.getKey().toString(), prop.getValue().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return configMap;
    }
}
