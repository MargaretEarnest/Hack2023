package database;

import jsonObjects.Employer;
import jsonObjects.Student;
import utils.Constants;

import java.sql.*;
import java.util.Objects;

public class EmployerDatabaseManager {

    private static EmployerDatabaseManager instance;

    public static EmployerDatabaseManager getInstance(){
        if(instance == null) {
            instance = new EmployerDatabaseManager();
        }
        return instance;
    }

    private final String url;
    private final String username;
    private final String password;

    private EmployerDatabaseManager() {
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

    public void addEmployer(Employer employer){
        try{
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            statement.executeUpdate(String.format("INSERT INTO Employer(Pname, Fname, Mname, Lname, Sname, Email, Departments, ProjectNames, University) values('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                    employer.getPrefix(), employer.getfName(), "", employer.getlName(), employer.getSuffix(), employer.getEmail(), "", "", employer.getUniversity().toString()));
            //TODO Complete fields
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateEmployer(Employer employer){
        try{
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            statement.executeUpdate(String.format("UPDATE Students SET Pname = '%s', Fname = '%s', Mname = '%s', Lname = '%s', Sname = %s, Departments = %s, ProjectNames = %s, University = '%s' WHERE email = '%s'",
                    employer.getPrefix(), employer.getfName(), "", employer.getlName(), employer.getSuffix(), "", "", employer.getUniversity().toString(), employer.getEmail()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Employer getEmployer(String email){
        try{
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            ResultSet result = statement.executeQuery(String.format("SELECT * FROM Employer WHERE email = '%s'", email));
            Employer employer = null;
            if (result.getFetchSize() > 0 && result.first()) {
                employer = new Employer(result.getString("Fname"), result.getString("Lname"), result.getString("Pname"), result.getString("Sname"), email, result.getString("University"));
            }
            return employer ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean containsEmployer(String email) {
        try{
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            ResultSet result = statement.executeQuery(String.format("SELECT * FROM Employers WHERE email = '%s'", email));
            return result.getFetchSize() > 0 && result.first();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
