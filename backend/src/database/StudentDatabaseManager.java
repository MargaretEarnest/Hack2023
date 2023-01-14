package database;

import jsonObjects.Student;
import utils.Constants;

import java.sql.*;
import java.util.Objects;

public class StudentDatabaseManager {

    private static StudentDatabaseManager instance;

    public static StudentDatabaseManager getInstance(){
        if(instance == null) {
            instance = new StudentDatabaseManager();
        }
        return instance;
    }

    private final String url;
    private final String username;
    private final String password;

    private StudentDatabaseManager() {
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

    public void addStudent(final Student student) {
        try{
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            statement.executeUpdate(String.format("INSERT INTO Students (email, prefix, fName, lName, suffix, status, majors, yearOfGraduation, gpa, classes) values('%s', '%s', '%s', '%s', '%s', %d, '%s', %d, %f, '%s')",
                    student.getEmail(), student.getPrefix(), student.getfName(), student.getlName(), student.getSuffix(), student.getStatus().getValue(), student.getMajors(), student.getYearOfGraduation(), student.getGPA(), student.getClasses()));
            //TODO Call to string on HashList
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStudent(final Student student) {
        try{
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            statement.executeUpdate(String.format("UPDATE Students SET prefix = '%s', fName = '%s', lName = '%s', suffix = '%s', status = %d, majors = '%s', yearOfGraduation = %d, gpa = %f, classes = '%s' WHERE email = '%s'",
                    student.getPrefix(), student.getfName(), student.getlName(), student.getSuffix(), student.getStatus().getValue(), student.getMajors(), student.getYearOfGraduation(), student.getGPA(), student.getClasses(), student.getEmail()));
            //TODO Call to string on HashList
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Student getStudent(final String email) {
        try{
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            ResultSet result = statement.executeQuery(String.format("SELECT * FROM Students WHERE email = '%s'", email));
            Student student = null;
            if (result.getFetchSize() > 0 && result.first()) {
                student = new Student(result.getString("email"), result.getString("prefix"), result.getString("fName"), result.getString("lName"), result.getString("suffix"), result.getInt("status"), result.getInt("yearOfGraduation"), result.getFloat("gpa"));
            }
            return student;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
