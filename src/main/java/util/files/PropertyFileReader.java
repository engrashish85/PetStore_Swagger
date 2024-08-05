package util.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyFileReader {

    public static Map<String, String> readProperties (String file) throws IOException {
        File configProperties = new File(file);
        Properties prop = new Properties();
        prop.load(new FileInputStream(configProperties));
        Map<String, String> properties = new HashMap<>();
        Enumeration<Object> KeyValues = prop.keys();
        while (KeyValues.hasMoreElements()) {
            String key = (String) KeyValues.nextElement();
            String value = prop.getProperty(key);
            properties.put(key, System.getProperty(key, value));
        }
        return properties;
    }
}
