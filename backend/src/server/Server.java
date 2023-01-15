package server;

import com.google.gson.Gson;
import database.*;
import jsonObjects.*;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import utils.HashList;

import java.net.InetSocketAddress;
import java.nio.charset.spi.CharsetProvider;
import java.util.List;
import java.util.Set;

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
            case "ValidateUser" -> handleValidateUser(conn, request);
            case "CreateStudent" -> handleCreateStudent(conn, request);
            case "CreateEmployer" -> handleCreateEmployer(conn, request);
            case "CreateJob" -> handleCreateJob(conn, request);
            case "StudentList" -> handleStudentList(conn, request);
            case "JobList" -> handleJobList(conn, request);
            case "ChooseStudent" -> handleChooseStudent(conn, request);
            case "JobLoadRequest" -> handleJobLoad(conn, request);
            case "ApplyToJob" -> handleApplyToJob(conn, request);
            default -> System.out.println("ERROR");
        }
    }

    private void handleApplyToJob(WebSocket conn, RequestHandler.Request request) {
        Gson gson = new Gson();
        ApplyToJobRequest applyToJobRequest = gson.fromJson(request.data, ApplyToJobRequest.class);
        ChosenJobDatabaseManager.getInstance().addChoice(applyToJobRequest.jobName(), applyToJobRequest.email());
    }

    private void handleJobLoad(WebSocket conn, RequestHandler.Request request) {
        Gson gson = new Gson();
        List<String> locations = JobDatabaseManager.getInstance().getAllLocations();
        MinMax hours = JobDatabaseManager.getInstance().getMinMaxHours();
        MinMax teamSize = JobDatabaseManager.getInstance().getMinMaxStudents();
        conn.send(gson.toJson(new JobLoadRequest(hours, teamSize, locations.toArray())));
    }

    private void handleChooseStudent(WebSocket conn, RequestHandler.Request request) {
        Gson gson = new Gson();
        StudentChooseRequest studentChooseRequest = gson.fromJson(request.data, StudentChooseRequest.class);
        ChosenJobDatabaseManager.getInstance().removeChoice(studentChooseRequest.jobName(), studentChooseRequest.email());
    }

    private void handleStudentList(WebSocket conn, RequestHandler.Request request) {
        Gson gson = new Gson();
        StudentListRequest studentListRequest = gson.fromJson(request.data, StudentListRequest.class);
        Object[] jobs = ChosenJobDatabaseManager.getInstance().getChoices(studentListRequest.JobTitle());
        conn.send(gson.toJson(new StudentListResponse(jobs)));
    }

    private void handleCreateJob(WebSocket conn, RequestHandler.Request request) {
        Gson gson = new Gson();
        CreateJobRequest cr = gson.fromJson(request.data, CreateJobRequest.class);
        JobDatabaseManager.getInstance().addJob(new Job(cr.ID, cr.title, cr.department, cr.location, cr.numStudents,
                cr.hours, cr.email, cr.federalWorkStudy, cr.desc, Course.parse(new HashList<>(cr.requirements)), cr.phone, cr.contact));
        conn.send(gson.toJson(new CreateResponse(true)));
    }

    private void handleJobList(WebSocket conn, RequestHandler.Request request) {
        Gson gson = new Gson();
        JobListRequest jobListRequest = gson.fromJson(request.data, JobListRequest.class);
        HashList<Job> allJobs = JobDatabaseManager.getInstance().getApplicableJobs(jobListRequest);
        conn.send(gson.toJson(allJobs.toArray()));
    }

    private void handleCreateStudent(WebSocket conn, RequestHandler.Request request){
        Gson gson = new Gson();
        StudentCreateRequest createStudentRequest = gson.fromJson(request.data, StudentCreateRequest.class);
        UserDatabaseManager.getInstance().addUser(createStudentRequest.student().getEmail(), createStudentRequest.password());
        StudentDatabaseManager.getInstance().addStudent(createStudentRequest.student());
        conn.send(gson.toJson(new CreateResponse(true)));
    }

    private void handleCreateEmployer(WebSocket conn, RequestHandler.Request request){
        Gson gson = new Gson();
        EmployerCreateRequest employerCreateRequest = gson.fromJson(request.data, EmployerCreateRequest.class);
        UserDatabaseManager.getInstance().addUser(employerCreateRequest.employer().getEmail(), employerCreateRequest.password());
        EmployerDatabaseManager.getInstance().addEmployer(employerCreateRequest.employer());
        conn.send(gson.toJson(new CreateResponse(true)));
    }

    private void handleValidateUser(WebSocket conn, RequestHandler.Request request){
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
            conn.send(gson.toJson(response));
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
        System.out.println("Server started on port " + getPort() + "!");
        setConnectionLostTimeout(0);
        setConnectionLostTimeout(100);
    }
}
