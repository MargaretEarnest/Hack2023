import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FECommunication {

    /*
    method for making a student from input from Jacob's json
     */
    public void makeStudent(String jsonString){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        Student stud = gson.fromJson(jsonString, Student.class);

        // determine jobs

    }


}
