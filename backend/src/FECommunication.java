import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jsonObjects.Student;

public class FECommunication {

    /*
    method for making a student from input from Jacob's json
     */
    public static void makeStudent(String jsonString){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        Student stud = gson.fromJson(jsonString, Student.class);
        System.out.println(stud);


//        String jsonString2 = gson.toJson(stud);
//        System.out.println(jsonString2);

        // determine jobs

    }


}
