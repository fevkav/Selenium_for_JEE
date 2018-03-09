package config;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;

public class ConfigProperties {

    private static Properties properties;

    public static final String propertyFilePath = System.getProperty("user.dir") + "\\application.properties";

    /******************************************************************************/
    /* Keys for application.properties */

    public static final String KEY_ROOTURL = "rooturl";
    public static final String KEY_LOGOID = "logoId";
    public static final String KEY_USER = "user";
    public static final String KEY_PASSWORD = "pw";


    /******************************************************************************/
    public ConfigProperties() {
        init();
    }

    private void init() {
        System.out.println("Loading Properties from " + propertyFilePath);
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(" application.properties not found at " + propertyFilePath);
        }
    }

    public String getRootUrl() {
        String value = properties.getProperty(KEY_ROOTURL);
        if (value != null)
            return value;
        else
            throw new RuntimeException(KEY_ROOTURL + " not specified in application.properties");
    }

    public String getUser() {
        String value = properties.getProperty(KEY_USER);
        if (value != null)
            return value;
        else
            throw new RuntimeException(KEY_USER + " not specified in application.properties");

    }

    public String getPassword() {
        String value = properties.getProperty(KEY_PASSWORD);
        if (value != null){
            value = String.valueOf(Base64.getDecoder().decode(value));
            return value;
        }
        else
            throw new RuntimeException(KEY_PASSWORD + " not specified in application.properties");

    }

    public String getlogoId() {
        String value = properties.getProperty(KEY_LOGOID);
        if (value != null)
            return value;
        else
            throw new RuntimeException(KEY_LOGOID + " not specified in application.properties");
    }

    // TODO Standard Titel "METRO Cash Handling System"

}
