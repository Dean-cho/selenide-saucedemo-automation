package com.saucedemo.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;

public class PropertyLoader {
    private static final String PROPERTY_FILE_PATH = "src/test/resources/properties.yaml";
    private static Config config;

    static {
        load();
    }

    private static void load() {
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.findAndRegisterModules();
            config = mapper.readValue(new File(PROPERTY_FILE_PATH), Config.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config from YAML", e);
        }
    }

    public static Config getConfig() {
        return config;
    }
}
