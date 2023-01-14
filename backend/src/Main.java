import server.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Server.getInstance().start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}