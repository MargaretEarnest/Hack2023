import jsonObjects.*;
import utils.HashList;

public class AlgTester {
    /**
     * Main testing algorithm.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        testProjectMatching();
    }

    /**
     * Tests the
     */
    public static void testProjectMatching() {
        Course balls = new Course("CS", 1420, "Balls");
        Student tony = new Student(
                "spacenebdude@gmail.com",
                "ms",
                "tony",
                "vooOOlO",
                "the thirdd",
                0,
                new HashList<>("CS", "MA"),
                2023,
                1.4f,
                new HashList<>(balls),
                University.findUniversity("WPI")
        );
        Employer dave = new Employer(
                "dave",
                "the man",
                "senora",
                "nuts",
                "davetheman@yahoo.net",
                University.findUniversity("WPI")
        );
        Job dirtEnthusiast = new Job(
                "1",
                "Dirt Enthusiast",
                "CS",
                "ur mom",
                10,
                10,
                "davtheman@yahoo.net",
                true,
                "it is what you think ;)",
                new HashList<>(balls),
                "508",
                "Dave the man"
        );
        dave.getJobs().add(dirtEnthusiast);
        System.out.println(dave.getUniversity().getEmployers().get(0).getJobs().get(0).getDepartment());
        System.out.println(tony.getFilteredJobs());


    }


}
