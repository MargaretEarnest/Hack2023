package utils;

import jsonObjects.Course;
import jsonObjects.University;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Constants {
    public static final HashList<String> majors = new HashList<>();
    public static University WPI = University.findUniversity("WPI");
    public static final Map<String, Course> courses = new HashMap<>();

    private static Constants instance;
    public static Constants getInstance() {
        if (instance == null) {
            instance = new Constants();
        }
        return instance;
    }

    //Store configs loaded from .conf files
    private final Map<String, Properties> confMap;

    private Constants() {
        confMap = new HashMap<>();
        try {
            loadConfig("sql", "sqlconfig.properties");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Properties getConfig(String conf) {
        return confMap.get(conf);
    }

    //Config format is name: config
    public void loadConfig(final String propName, final String confFile) throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(confFile);

        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + confFile + "' not found in classpath");
        }

        confMap.put(propName, prop);
    }
}
