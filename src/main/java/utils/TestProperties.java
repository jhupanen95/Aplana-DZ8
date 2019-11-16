package utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

    private final Properties properties = new Properties();
    private static TestProperties INSTANCE = null;

    private TestProperties(){
        try {
            properties.load(new FileReader(new File("C:\\Users\\jhupanen\\IdeaProjects\\Aplana\\Aplana-DZ4\\src\\environment.properties"))); //properties.load(new FileReader(new File("./" + System.getProperty("environment") + ".properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static TestProperties getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TestProperties();
        }
        return INSTANCE;
    }
    public Properties getProperties(){
        return properties;
    }
}
