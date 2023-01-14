package database;

import jsonObjects.Course;
import jsonObjects.Job;
import utils.Constants;
import utils.HashList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JobDatabaseManager {

    private static JobDatabaseManager instance;

    public static JobDatabaseManager getInstance(){
        if(instance == null) {
            instance = new JobDatabaseManager();
        }
        return instance;
    }

    private final String url;
    private final String username;
    private final String password;

    private JobDatabaseManager() {
        username = Constants.getInstance().getConfig("sql").getProperty("username");
        password = Constants.getInstance().getConfig("sql").getProperty("password");
        url = Constants.getInstance().getConfig("sql").getProperty("url");
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

    public void addJob(Job job){
        try{
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            statement.executeUpdate(String.format("INSERT INTO Jobs (ID, Title, Department, Location, StudentsRequired, WeeklyHours, FederalFundingRequired, ContactName, Email, Phone, JobDescription, Requirements) VALUES" +
                    "(UUID(), '%s', '%s', '%s', %d, %d, %b, '%s', '%s', '%s', '%s', '%s'"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateJob(Job job){
        try{
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            statement.executeUpdate(String.format("UPDATE Jobs SET Title = '%s', Department = '%s', Location = '%s', StudentsRequired = %d, WeeklyHours = %d, FederalFundingRequired = %b, ContactName = '%s', Email = '%s', Phone = '%s', JobDescription = '%s', Requirements = '%s' WHERE ID = '%s'",
                    job.getTitle(), job.getDepartment(), job.getLocation(), job.getNumStudents(), job.getHours(), job.isFederalWorkStudy(), job.getContact(), job.getEmail(), job.getPhone(), job.getDesc(), job.getRequirements().toString(), job.getId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Job getJob(String id){
        try{
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            ResultSet result = statement.executeQuery("Select * from Jobs WHERE ID = '" + id + "'");
            if(result.getFetchSize() > 0 && result.first()){
                return new Job(id, result.getString("Title"), result.getString("Department"), result.getString("Location"), result.getInt("StudentsRequired"), result.getInt("WeeklyHours"), result.getString("Email"), result.getBoolean("FederalFundingRequired"), result.getString("JobDescription"), Course.parse(HashList.parse(result.getString("Requirements"), "|")), result.getString("Phone"), result.getString("ContactName"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<String> getAllJobsIds(){
        try{
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            ResultSet results = statement.executeQuery("SELECT ID FROM Jobs");
            List<String> ids = new ArrayList<>();
            while(results.next()){
                ids.add(results.getString("ID"));
            }
            return ids;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getAllLocations(){
        try{
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            ResultSet results = statement.executeQuery("SELECT Location FROM Jobs");
            List<String> ids = new ArrayList<>();
            while(results.next()){
                ids.add(results.getString("Location"));
            }
            return ids;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    record MinMax(double min, double max){}

    public MinMax getMinMaxStudents(){
        try{
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            ResultSet minResults = statement.executeQuery("SELECT MAX(StudentsRequired) FROM Jobs");
            ResultSet maxResults = statement.executeQuery("SELECT MIN(StudentsRequired) FROM Jobs");
            double min = 0.0;
            double max = 0.0;
            if(minResults.getFetchSize() > 0 && minResults.first()){
                min = minResults.getInt("StudentsRequired");
            }
            if(maxResults.getFetchSize() > 0 && maxResults.first()){
                max = maxResults.getInt("StudentsRequired");
            }
            return new MinMax(min, max);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public MinMax getMinMaxHours(){
        try{
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            ResultSet minResults = statement.executeQuery("SELECT MAX(WeeklyHours) FROM Jobs");
            ResultSet maxResults = statement.executeQuery("SELECT MIN(WeeklyHours) FROM Jobs");
            double min = 0.0;
            double max = 0.0;
            if(minResults.getFetchSize() > 0 && minResults.first()){
                min = minResults.getInt("WeeklyHours");
            }
            if(maxResults.getFetchSize() > 0 && maxResults.first()){
                max = maxResults.getInt("WeeklyHours");
            }
            return new MinMax(min, max);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
