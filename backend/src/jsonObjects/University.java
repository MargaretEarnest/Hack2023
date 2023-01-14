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
     * Gets the list of {@code Employers} from this {@code University}.
     * @return {@code this.employers}
     */
    public HashList<Employer> getEmployers() {
        return this.employers;
    }

    /**
     * Gets the list of {@code Students} at this {@code University}.
     * @return {@code this.students}
     */
    public HashList<Student> getStudents() {
        return this.students;
    }

    /**
     * Gets the list of {@code Courses} offered at this {@code University}.
     * @return {@code this.courses}
     */
    public HashList<Course> getCourses() {
        return this.courses;
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
