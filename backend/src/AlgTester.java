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
        Course eggs = new Course("MA", 2240, "Eggs");
        Course balls = new Course("CS", 1420, "Balls");
        Course orbs = new Course("ECE", 3301, "Orbs");
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
                new HashList<>(balls, eggs),
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
                new HashList<>(orbs),
                "508",
                "Dave the man",
                0
        );
        Job busCyclist = new Job(
                "3",
                "Bus Cyclist",
                "CS",
                "stratton 203",
                1,
                16,
                "davtheman@yahoo.net",
                false,
                "looking for someone to ride a cycle to pull the bus",
                new HashList<>(eggs, balls),
                "508",
                "Dave the man",
                1
        );
        Job coder = new Job(
                "3",
                "Coder",
                "CS",
                "stratton 20",
                1,
                7,
                "davtheman@yahoo.net",
                true,
                "need a pro coder",
                new HashList<>(balls, orbs),
                "508",
                "Dave the man",
                1
        );
        Job nailBiter = new Job(
                "3",
                "Nail Biter",
                "CS",
                "",
                1,
                16,
                "davtheman@yahoo.net",
                false,
                "looking for someone to ride a cycle to pull the bus",
                new HashList<>(eggs),
                "508",
                "Dave the man",
                2
        );
        dave.getJobs().add(dirtEnthusiast);
        dave.getJobs().add(busCyclist);
        dave.getJobs().add(coder);
        dave.getJobs().add(nailBiter);
        //System.out.println(dave.getUniversity().getEmployers().get(0).getJobs().size());
        //tony.getFilteredJobs();
        System.out.println(tony.getFilteredJobs());


    }


}
