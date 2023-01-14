// copied from online https://www.tutorialspoint.com/gson/gson_quick_guide.htm
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonTest {
    public static void main(String[] args) {
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
        return "Student [ name: "+name+", age: "+ age+ " ]";
    }
}