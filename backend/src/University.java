public class University {
    // The name of the university.
    private final String name;
    // A list of all employers at this university with an account.
    private final HashList<Employer> employers;
    // A list of all students at this university with an account.
    private final HashList<Student> students;

    /**
     * Creates a new {@code University}.
     * @param name the name of this {@code University}.
     */
    public University(String name) {
        this.name = name;
        this.employers = new HashList<>();
        this.students = new HashList<>();
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
     * Converts this {@code University} to a printable format.
     * @return this {@code University} as a {@code String}.
     */
    public String toString() {
        return this.name;
    }
}
