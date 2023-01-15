// copied from online https://www.tutorialspoint.com/gson/gson_quick_guide.htm
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonTest {

    // Run to test makeStudent
    public static void main(String[] args) {

        String margsstring = "{\"email\":\"mrjakob@wpi.edu\",\"prefix\":\"Mr.\",\"fName\":\"Jakob\",\"lName\":\"Misbsfkuhjfdjkheef\",\"suffix\":\"the last\",\"status\":0,\"yearOfGraduation\":2023,\"gpa\":1}";
        System.out.println(margsstring);
        FECommunication.makeStudent(margsstring);
    }

    // Practice to see if gson.fromJson works
    public static void mainPractice(String[] args) {
        String jsonString = "{\"name\":\"Tony\", \"age\":21}";

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        StudentTest student = gson.fromJson(jsonString, StudentTest.class);
        System.out.println(student);

        jsonString = gson.toJson(student);
        System.out.println(jsonString);
    }
}

// Practice Student class for testing method
class StudentTest {
    private String name;
    private int age;
    public StudentTest(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "jsonObjects.Student [ name: "+name+", age: "+ age+ " ]";
    }
}