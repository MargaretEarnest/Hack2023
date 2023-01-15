package database;

import utils.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChosenJobDatabaseManager {


    private static ChosenJobDatabaseManager instance;

    public static ChosenJobDatabaseManager getInstance() {
        if (instance == null) {
            instance = new ChosenJobDatabaseManager();
        }
        return instance;
    }

    private final String url;
    private final String username;
    private final String password;

    private ChosenJobDatabaseManager() {
        username = Constants.getInstance().getConfig("sql").getProperty("username");
        password = Constants.getInstance().getConfig("sql").getProperty("password");
        url = Constants.getInstance().getConfig("sql").getProperty("url");
        getConnection();
    }

    private Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("couldn't connect!");
        }
        return null;

    }

    public void addChoice(String jobName, String email){
        try{
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            statement.executeUpdate(String.format("INSERT INTO ChosenJobs (JobName, Email) VALUES ('%s', '%s')", jobName, email));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeChoice(String jobName, String email){
        try{
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            statement.executeUpdate(String.format("DELETE FROM ChosenJobs WHERE JobName = '%s' and Email = '%s'", jobName, email));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Object[] getChoices(String jobName){
        try{
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            ResultSet results = statement.executeQuery(String.format("SELECT Email From ChosenJobs WHERE JobName= '%s'", jobName));
            List<String> students = new ArrayList<>();
            while(results.next()){
                students.add(results.getString("Email"));
            }
            return students.toArray();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
