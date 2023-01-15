package database;

import com.google.gson.Gson;
import jsonObjects.Course;
import jsonObjects.Student;
import jsonObjects.StudentJson;
import jsonObjects.University;
import utils.Constants;
import utils.HashList;

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

    public void addStudent(final StudentJson student) {
        try{
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            Gson gson = new Gson();
            statement.executeUpdate(String.format("INSERT INTO Students (email, prefix, fName, lName, Sname, position, majors, yearOfGraduation, gpa, courses) values('%s', '%s', '%s', '%s', '%s', %d, '%s', %d, %f, '%s')",
                    student.getEmail(), student.getPrefix(), student.getfName(), student.getlName(), student.getSuffix(), student.getStatus(), gson.toJson(student.getMajors()), student.getYearOfGraduation(), student.getGpa(), gson.toJson(student.getClasses())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //TODO Fix this if it ever gets used. The field names are wrong
    public void updateStudent(final Student student) {
        try{
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            statement.executeUpdate(String.format("UPDATE Students SET prefix = '%s', fName = '%s', lName = '%s', suffix = '%s', status = %d, majors = '%s', yearOfGraduation = %d, gpa = %f, classes = '%s' WHERE email = '%s'",
                    student.getPrefix(), student.getfName(), student.getlName(), student.getSuffix(), student.getStatus().getValue(), student.getMajors().serialize(), student.getYearOfGraduation(), student.getGPA(), student.getClasses().serialize(), student.getEmail()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Student getStudent(final String email) {
        try{
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            ResultSet result = statement.executeQuery(String.format("SELECT * FROM Students WHERE email = '%s'", email));
            Student student = null;
            if (result.next()) {
                student = new Student(result.getString("email"), result.getString("prefix"), result.getString("fName"), result.getString("lName"), result.getString("sName"), result.getInt("position"), new HashList<>(new Gson().fromJson(result.getString("majors"), String[].class)), result.getInt("yearOfGraduation"), result.getFloat("gpa"), Course.parse(new HashList<>(new Gson().fromJson(result.getString("courses"), String[].class))), University.findUniversity(result.getString("university")));
            }
            return student;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean containsStudent(String email) {
        try{
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            ResultSet result = statement.executeQuery(String.format("SELECT * FROM Students WHERE email = '%s'", email));
            return result.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
