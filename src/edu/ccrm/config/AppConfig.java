package edu.ccrm.config;

import java.nio.file.Path;
import java.nio.file.Paths;

//singleton class to hold application config
public class AppConfig {
    // single instance of appConfig 
    private static AppConfig instance;

    // dirctory path where data files will be stored
    private final Path dataDir;

    // Pvt constructor prevents instantiation from outside this class
    private AppConfig() {
        //  initialize datadir to data folder inside current working directory
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