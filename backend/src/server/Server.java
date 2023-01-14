package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{

    private static Server instance;

    public static Server getInstance() throws IOException {
        if(instance == null) {
            instance = new Server();
        }
        return instance;
    }

    private boolean running;
    private Thread thread;
    private final int port = 8000;
    private final ServerSocket server;

    public Server() throws IOException {
        this.running = false;
        this.server = new ServerSocket(port);
    }

    @Override
    public void run() {
        while(running){
            try {
                Socket client = server.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                System.out.println("Connection Established");
                String inputLine;
                while((inputLine = in.readLine()) != null){
                    System.out.println(inputLine);
                    out.println("Hello");
                }
                client.close();
                in.close();
                System.out.println("Connection Closed");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void start(){
        if(!running){
            this.thread = new Thread(this);
            this.thread.start();
            running = true;
        }
    }

    public synchronized void stop(){
        if(!running)return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
