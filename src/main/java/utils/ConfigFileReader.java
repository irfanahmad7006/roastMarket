package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    private Properties prop;
    private String filePath = "src/test/resources/config.properties";
    public Properties initiateProperties(){
        prop = new Properties();
        FileInputStream ip = null;
        try {
            ip = new FileInputStream(filePath);
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
