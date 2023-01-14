package jsonObjects;

import utils.HashList;

public class University {
    // The name of the university.
    private final String name;
    // A list of all employers at this university with an account.
    private final HashList<Employer> employers;
    // A list of all students at this university with an account.
    private final HashList<Student> students;

    /**
     * Creates a new {@code jsonObjects.University}.
     * @param name the name of this {@code jsonObjects.University}.
     */
    public University(String name) {
        this.name = name;
        this.employers = new HashList<>();
        this.students = new HashList<>();
    }

    /**
     * Adds a new {@code jsonObjects.Employer} to this {@code jsonObjects.University}.
     * @param employer the new {@code jsonObjects.Employer}.
     */
    public void addEmployer(Employer employer) {
        this.employers.add(employer);
    }

    /**
     * Determines whether this {@code jsonObjects.University} employs this {@code jsonObjects.Employer}.
     * @param employer the target {@code jsonObjects.Employer}.
     * @return {@code true} if the specified {@code jsonObjects.Employer} works for this {@code jsonObjects.University},
     * else {@code false}.
     */
    public boolean containsEmployer(Employer employer) {
        return this.employers.contains(employer);
    }

    /**
     * Adds a new {@code jsonObjects.Student} to this {@code jsonObjects.University}.
     * @param student the new {@code jsonObjects.Student}.
     */
    public void addStudent(Student student) {
        this.students.add(student);
    }

    /**
     * Determines whether this {@code jsonObjects.University} employs this {@code jsonObjects.Student}.
     * @param student the target {@code jsonObjects.Student}.
     * @return {@code true} if the specified {@code jsonObjects.Student} works for this {@code jsonObjects.University},
     * else {@code false}.
     */
    public boolean containsStudent(Student student) {
        return this.students.contains(student);
    }

    /**
     * Converts this {@code jsonObjects.University} to a printable format.
     * @return this {@code jsonObjects.University} as a {@code String}.
     */
    public String toString() {
        return this.name;
    }
}
