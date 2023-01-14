package server;

import com.google.gson.Gson;

public class RequestHandler {

    public static Request parseRequest(String requestString) {
        Gson gson = new Gson();
        return gson.fromJson(requestString, Request.class);
    }

    public static class Request {
        final String type;
        final String data;

        public Request(final String type, final String data) {
            this.type = type;
            this.data = data;
        }
    }
}
