package edu.ccrm.config;

import java.nio.file.Path;
import java.nio.file.Paths;

// Singleton to hold configuration
public class AppConfig {
    private static AppConfig instance;
    private final Path dataDir;

    private AppConfig() {
        this.dataDir = Paths.get(System.getProperty("user.dir"), "data");
    }

    public static synchronized AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    public Path getDataDir() {
        return dataDir;
    }
}
