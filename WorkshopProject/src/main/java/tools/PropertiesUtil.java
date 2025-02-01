package tools;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    private Properties properties;
    private String fileName;

    public PropertiesUtil(String propertyFilePath){
        fileName = propertyFilePath;
        if(!fileName.trim().toLowerCase().endsWith(".properties")){
            fileName = propertyFilePath + ".properties";
        }
        properties = getProperties(fileName);
    }

    private Properties getProperties(String fileName) {
        try {
            InputStream stream;
            stream = ClassLoader.getSystemResourceAsStream(fileName);
            Properties properties = new Properties();
            properties.load(stream);
            return properties;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getValue(String keyName){
        if (properties.containsKey(keyName)) {
            return properties.getProperty(keyName);
        } else {
            return null;
        }
    }

    public void addOrReplaceValue(String uiObjectKeyName, String uiObjectValue){
        if (properties.containsKey(uiObjectKeyName)) {
            properties.replace(uiObjectKeyName, uiObjectValue);
        } else {
            properties.put(uiObjectKeyName, uiObjectValue);
        }
    }

}

