package database;

import utils.Constants;
import utils.PasswordStorage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

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

    public void addUser(final String email, final String password) {
        try{
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            statement.executeUpdate(String.format("INSERT INTO Users(Email, Pass) values('%s', '%s')", email, PasswordStorage.createHash(password)));
        } catch (SQLException | PasswordStorage.CannotPerformOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validateUser(final String email, final String password){
        try{
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            ResultSet result = statement.executeQuery(String.format("SELECT Pass FROM Users WHERE email = '%s'", email));
            if(result.getFetchSize() > 0 && result.first()){
                PasswordStorage.verifyPassword(result.getString(password), password);
            }
        } catch (SQLException | PasswordStorage.InvalidHashException | PasswordStorage.CannotPerformOperationException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
