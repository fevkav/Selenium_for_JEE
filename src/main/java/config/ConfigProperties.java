package config;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;

/**
 * A helper class to get the properties from the application.properties file, e.g. user and password to login
 * or locators such as id to find WebElements.
 */
public class ConfigProperties {

    private static Properties properties;

    private static final String PROPERTY_FILE_PATH = System.getProperty("user.dir") + "\\application.properties";

    /*******************************   KEYS SECTION   ******************************/


    private static final String KEY_ROOTURL = "rooturl";
    private static final String KEY_LOGOID = "logoId";
    private static final String KEY_USER = "user";
    private static final String KEY_PASSWORD = "pw";


    /******************************************************************************/
    public ConfigProperties() {
        init();
    }

    private void init() {
        System.out.println("Loading Properties from " + PROPERTY_FILE_PATH);
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(PROPERTY_FILE_PATH));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(" application.properties not found at " + PROPERTY_FILE_PATH);
        }
    }

    /**
     * @return the complete root URL specified in application.properties.
     */
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
        if (value != null) {
            value = new String(Base64.getDecoder().decode(value.getBytes()));
            return value;
        } else
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
