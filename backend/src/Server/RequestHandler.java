package Server;

public class RequestHandler {
    enum RequestType {
        Login,
        Register,
        AddJob,
        GetMatches,
        RequestJob,
        RequestStudent,
        UpdateJob,
        UpdateStudentInfo
    }

    public static Request parseRequest(String requestString) {

        return null;
    }

    public static class Request {
        final RequestType type;
        final String data;

        public Request(final RequestType type, final String data) {
            this.type = type;
            this.data = data;
        }
    }
}
