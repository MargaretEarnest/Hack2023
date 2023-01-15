package database;

import jsonObjects.Course;
import jsonObjects.Job;
import jsonObjects.JobListRequest;
import jsonObjects.MinMax;
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

    /**
     * Provides a list of all {@code Jobs} with a set of given IDs.
     * @param ids the iterable list of IDs.
     * @return the list of {@code Jobs}.
     */
    public HashList<Job> getAllJobs(Iterable<String> ids) {
        try {
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            final HashList<Job> jobs = new HashList<>();
            for(String id : ids) {
                ResultSet result = statement.executeQuery("Select * from Jobs WHERE ID = '" + id + "'");
                if(result.getFetchSize() > 0 && result.first()){
                    jobs.add(new Job(id, result.getString("Title"), result.getString("Department"), result.getString("Location"), result.getInt("StudentsRequired"), result.getInt("WeeklyHours"), result.getString("Email"), result.getBoolean("FederalFundingRequired"), result.getString("JobDescription"), Course.parse(HashList.parse(result.getString("Requirements"), "|")), result.getString("Phone"), result.getString("ContactName")));
                }
            }
            return jobs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    public MinMax getMinMaxStudents(){
        try{
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            ResultSet minResults = statement.executeQuery("SELECT MAX(StudentsRequired) AS StudentsRequired FROM Jobs");
            ResultSet maxResults = statement.executeQuery("SELECT MIN(StudentsRequired) AS StudentsRequired FROM Jobs");
            double min = 0.0;
            double max = 0.0;
            if(!minResults.isClosed() && minResults.next()){
                min = minResults.getInt("StudentsRequired");
            }
            if(!maxResults.isClosed() && maxResults.next()){
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
            ResultSet minResults = statement.executeQuery("SELECT MIN(WeeklyHours) AS WeeklyHours FROM Jobs");
            ResultSet maxResults = statement.executeQuery("SELECT MAX(WeeklyHours) AS WeeklyHours FROM Jobs");
            double min = 0.0;
            double max = 0.0;
            if(!minResults.isClosed() && minResults.next()){
                min = minResults.getInt("WeeklyHours");
            }
            if(!maxResults.isClosed() && maxResults.next()){
                max = maxResults.getInt("WeeklyHours");
            }
            return new MinMax(min, max);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets a list of all {@code Jobs} fitting a particular set of filters.
     * @param request the {@code JobListRequest}.
     * @return the filtered list.
     */
    public HashList<Job> getApplicableJobs(JobListRequest request) {
        try {
            String SQLRequest = "SELECT * FROM Jobs" + // Builds initial part of SQL string
                    " WHERE WeeklyHours >= " + request.hours().min() +
                    " AND WeeklyHours <= " + request.hours().max() +
                    " AND StudentsRequired >= " + request.teamSize().min() +
                    " AND StudentsRequired <= " + request.teamSize().max()
                    ;
            if(request.status().length > 0) { // Builds the status constraints
                String OR = "";
                StringBuilder statuses = new StringBuilder();
                for(String status : request.status()) {
                    statuses.append(OR).append(status);
                    OR = " OR ";
                }
                SQLRequest = SQLRequest.concat(" AND (" + statuses + ")");
            }
            if(request.majors().length > 0) { // Builds the majors constraints
                String OR = "";
                StringBuilder majors = new StringBuilder();
                for(String major : request.majors()) {
                    majors.append(OR).append(major);
                    OR = " OR ";
                }
                SQLRequest = SQLRequest.concat(" AND (" + majors + ")");
            }
            if(request.departments().length > 0) { // Builds the department constraints
                String OR = "";
                StringBuilder departments = new StringBuilder();
                for(String department : request.departments()) {
                    departments.append(OR).append(department);
                    OR = " OR ";
                }
                SQLRequest = SQLRequest.concat(" AND (" + departments + ")");
            }
            if(request.locations().length > 0) { // Builds the location constraints
                String OR = "";
                StringBuilder locations = new StringBuilder();
                for(String location : request.locations()) {
                    locations.append(OR).append(location);
                    OR = " OR ";
                }
                SQLRequest = SQLRequest.concat(" AND (" + locations + ")");
            }
            if(request.federalWorkStudy()) {
                SQLRequest = SQLRequest.concat(" AND FederalFundingRequired = \'TRUE\'");
            }
            // Runs the constructed SQL command to find the filtered job results.
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            ResultSet result = statement.executeQuery(SQLRequest);
            final HashList<Job> jobs = new HashList<>();
            while(result.next()) {
                jobs.add(new Job(result.getString("ID"), result.getString("Title"), result.getString("Department"), result.getString("Location"), result.getInt("StudentsRequired"), result.getInt("WeeklyHours"), result.getString("Email"), result.getBoolean("FederalFundingRequired"), result.getString("JobDescription"), Course.parse(HashList.parse(result.getString("Requirements"), "|")), result.getString("Phone"), result.getString("ContactName")));
            }
            return jobs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
