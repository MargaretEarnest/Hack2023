package server;

import com.google.gson.Gson;
import database.EmployerDatabaseManager;
import database.StudentDatabaseManager;
import database.UserDatabaseManager;
import jsonObjects.*;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class Server extends WebSocketServer {
    public Server(int port) {
        super(new InetSocketAddress(port));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println(
                conn.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println(conn + ": " + message);
        RequestHandler.Request request = RequestHandler.parseRequest(message);
        switch (request.type) {
            case "CreateUser" -> handleCreateUser(conn, request);
            case "ValidateUser" -> handleValidateUser(conn, request);
            default -> System.out.println("ERROR");
        }
    }

    private void handleCreateUser(WebSocket conn, RequestHandler.Request request){
        Gson gson = new Gson();
        UserCreate userCreate = gson.fromJson(request.data, UserCreate.class);
        UserDatabaseManager.getInstance().addUser(userCreate.email(), userCreate.password());
    }

    private void handleValidateUser(WebSocket conn, RequestHandler.Request request){
        System.out.println("VALIDATE USER");
        Gson gson = new Gson();
        Login login = gson.fromJson(request.data, Login.class);
        System.out.println(request.data);
        boolean valid = UserDatabaseManager.getInstance().validateUser(login.email(), login.password());
        if(valid){
            String name = "";
            boolean isStudent = false;
            if(StudentDatabaseManager.getInstance().containsStudent(login.email())){
                Student student = StudentDatabaseManager.getInstance().getStudent(login.email());
                isStudent = true;
                name = student.getPrefix() + " " + student.getfName() + " " +  student.getlName() + " " +  student.getSuffix();
            }else if(EmployerDatabaseManager.getInstance().containsEmployer(login.email())){
                Employer employer = EmployerDatabaseManager.getInstance().getEmployer(login.email());
                name = employer.getPrefix() + " " + employer.getfName() + " " +  employer.getlName() + " " +  employer.getSuffix();
            }else{
                valid = false;
            }
            LoginResponse response = new LoginResponse(valid, name, isStudent);
            conn.send(gson.toJson(response));
        }else{
            LoginResponse response = new LoginResponse(false, "", false);
            broadcast(gson.toJson(response));
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
        if (conn != null) {
            // some errors like port binding failed may not be assignable to a specific websocket
        }
    }

    @Override
    public void onStart() {
        System.out.println("Server started!");
        setConnectionLostTimeout(0);
        setConnectionLostTimeout(100);
    }
}
