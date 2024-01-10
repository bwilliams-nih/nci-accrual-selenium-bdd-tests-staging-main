package gov.nci.ctrp.accrual.utils;

import java.io.*;
import java.util.Properties;

public class ConfigFileReader {
    private Properties properties;
    private Properties envProperties;
    private final String propertyFilePath = "src/test/resources/properties/environment.properties";
    public ConfigFileReader(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            envProperties = new Properties();
            try {
                envProperties.load(reader);
                try{
                    String path = "src/test/resources/properties/" + envProperties.getProperty("env") + ".properties";
                    try {
                        reader = new BufferedReader(new FileReader(path));
                        properties = new Properties();
                        try {
                            properties.load(reader);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    } catch (FileNotFoundException | RuntimeException e) {
                        throw new RuntimeException(e);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public long getImplicitlyWait() {
        String webdriverwait = properties.getProperty("webdriverwait");
        if(webdriverwait != null) return Long.parseLong(webdriverwait);
        else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");
    }

    public String getApplicationUrl() {
        String url = properties.getProperty("url");
        if(url != null) return url;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    public DriverType getBrowser() {
        String browserName = properties.getProperty("browser");

        return switch (browserName.toLowerCase()) {
            case "", "chrome-headless", "chromeheadless", "headless" -> DriverType.CHROMEHEADLESS;
            case "chrome" -> DriverType.CHROME;
            case "firefox" -> DriverType.FIREFOX;
            case "edge" -> DriverType.EDGE;
            default ->
                    throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
        };

    }

    public Boolean getBrowserWindowSize() {
        String windowSize = properties.getProperty("windowMaximize");
        if(windowSize != null) return Boolean.valueOf(windowSize);
        return true;
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }

    public String getEnv(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(propertyFilePath));
            envProperties = new Properties();
            envProperties.load(reader);
            return envProperties.getProperty("env");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
