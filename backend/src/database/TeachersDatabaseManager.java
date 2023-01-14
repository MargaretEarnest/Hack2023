package database;

import utils.Constants;

import java.sql.Connection;
import java.sql.DriverManager;

public class TeachersDatabaseManager {

    private static TeachersDatabaseManager instance;

    public static TeachersDatabaseManager getInstance(){
        if(instance == null) {
            instance = new TeachersDatabaseManager();
        }
        return instance;
    }

    private final String url;
    private final String username;
    private final String password;

    private TeachersDatabaseManager() {
        username = Constants.getInstance().getConfig("sql").getProperty("username");
        password = Constants.getInstance().getConfig("sql").getProperty("password");
        url = Constants.getInstance().getConfig("sql").getProperty("url");
        System.out.println(username);
        System.out.println(password);
        System.out.println(url);
        getConnection();
    }

    private Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, username, password);
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("couldn't connect!");
        }
        return null;
    }
}
