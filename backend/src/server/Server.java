package server;

import com.google.gson.Gson;
import database.UserDatabaseManager;
import jsonObjects.Login;
import jsonObjects.UserCreate;
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
        conn.send("Welcome to the server!"); //This method sends a message to the new client
        broadcast("new connection: " + handshake
                .getResourceDescriptor()); //This method sends a message to all clients connected
        System.out.println(
                conn.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        broadcast(conn + " has left the room!");
        System.out.println(conn + " has left the room!");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        broadcast(message);
        System.out.println(conn + ": " + message);
        RequestHandler.Request request = RequestHandler.parseRequest(message);
        switch(request.type){
            case "CreateUser":
                handleCreateUser(request);
                break;
            case "ValidateUser":
                handleValidateUser(request);
                break;
            default:
                System.out.println("ERROR");
                break;
        }
    }

    private void handleCreateUser(RequestHandler.Request request){
        Gson gson = new Gson();
        UserCreate userCreate = gson.fromJson(request.data, UserCreate.class);
        UserDatabaseManager.getInstance().addUser(userCreate.email(), userCreate.password());
    }

    private void handleValidateUser(RequestHandler.Request request){
        System.out.println("VALIDATE USER");
        Gson gson = new Gson();
        Login login = gson.fromJson(request.data, Login.class);
        System.out.println(request.data);
        boolean valid = UserDatabaseManager.getInstance().validateUser(login.email(), login.password());
        //TODO Send back if user is valid
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
