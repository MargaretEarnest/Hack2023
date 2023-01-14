package database;

import utils.Constants;

import java.sql.Connection;
import java.sql.DriverManager;

public class UserDatabaseManager {

    private static UserDatabaseManager instance;

    public static UserDatabaseManager getInstance(){
        if(instance == null) {
            instance = new UserDatabaseManager();
        }
        return instance;
    }

    private final String url;
    private final String username;
    private final String password;

    private UserDatabaseManager() {
        username = Constants.getInstance().getConfig("sql").getProperty("username");
        password = Constants.getInstance().getConfig("sql").getProperty("password");
        url = Constants.getInstance().getConfig("sql").getProperty("url");
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

    public void addUser() {

    }
}
