package jsonObjects;

import utils.HashList;

import java.util.HashMap;
import java.util.Map;

public class University {
    // A list of all recorded universities.
    private static final Map<String, University> universities = new HashMap<>();

    // The name of the university.
    private final String name;
    // A list of all employers at this university with an account.
    private final HashList<Employer> employers;
    // A list of all students at this university with an account.
    private final HashList<Student> students;
    // A list of all courses at this university.
    private final HashList<Course> courses;

    /**
     * Creates a new {@code jsonObjects.University}.
     * @param name the name of this {@code jsonObjects.University}.
     */
    private University(String name) {
        this.name = name;
        this.employers = new HashList<>();
        this.students = new HashList<>();
        this.courses = new HashList<>();
    }

    /**
     * Finds a specified {@code University} or creates a new one.
     * @param name the name of thsi target {@code University}.
     * @return the {@code University} object in the list if one exists, else a new
     * {@code University} object with the given name.
     */
    public static University findUniversity(String name) {
        University university = universities.get(name);
        if(university == null) {
            universities.put(name, new University(name));
            return new University(name);
        }
        return university;
    }

    /**
     * Adds a new {@code Employer} to this {@code University}.
     * @param employer the new {@code Employer}.
     */
    public void addEmployer(Employer employer) {
        this.employers.add(employer);
    }

    /**
     * Determines whether this {@code University} employs this {@code Employer}.
     * @param employer the target {@code Employer}.
     * @return {@code true} if the specified {@code Employer} works for this {@code University},
     * else {@code false}.
     */
    public boolean containsEmployer(Employer employer) {
        return this.employers.contains(employer);
    }

    /**
     * Adds a new {@code Student} to this {@code University}.
     * @param student the new {@code Student}.
     */
    public void addStudent(Student student) {
        this.students.add(student);
    }

    /**
     * Determines whether this {@code University} employs this {@code Student}.
     * @param student the target {@code Student}.
     * @return {@code true} if the specified {@code Student} works for this {@code University},
     * else {@code false}.
     */
    public boolean containsStudent(Student student) {
        return this.students.contains(student);
    }

    /**
     * Adds a new {@code Course} to this {@code University}.
     * @param course the new {@code Course}.
     */
    public void addCourse(Course course) {
        this.courses.add(course);
    }

    /**
     * Determines whether this {@code University} offers this {@code Course}.
     * @param course the target {@code Course}.
     * @return {@code true} if the specified {@code Course} is offered at this {@code University},
     * else {@code false}.
     */
    public boolean containsCourse(Course course) {
        return this.courses.contains(course);
    }

    /**
     * Gets the hashCode for this {@code University}.
     * @return the hashCode.
     */
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    /**
     * Converts this {@code University} to a printable format.
     * @return this {@code University} as a {@code String}.
     */
    @Override
    public String toString() {
        return this.name;
    }
}
