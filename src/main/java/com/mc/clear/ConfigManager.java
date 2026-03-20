package com.mc.clear;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = FabricLoader.getInstance().getConfigDir().resolve("clearitems.json").toFile();
    public static Config config = new Config();

    public static class Config {
        public int interval = 300;
        public boolean enabled = false; // 默认关闭，确保安全
        public boolean showActionBar = true;
        public List<String> whiteList = new ArrayList<>();
    }

    public static void loadConfig() {
        try {
            if (CONFIG_FILE.exists()) {
                try (FileReader reader = new FileReader(CONFIG_FILE)) {
                    config = GSON.fromJson(reader, Config.class);
                    if (config == null) config = new Config();
                }
            } else {
                saveConfig();
            }
        } catch (Exception e) {
            System.err.println("[ClearItems] 配置文件解析失败，正在使用默认配置: " + e.getMessage());
            config = new Config();
            saveConfig();
        }
    }

    public static void saveConfig() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(config, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}