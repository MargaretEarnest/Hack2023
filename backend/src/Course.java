public class Course {
    // The department and course name.
    private final String department, name;
    // The integer ID for this course.
    private final int ID;

    /**
     * Creates a new {@code Course}.
     * @param department the department.
     * @param ID the integer course ID.
     * @param name the name of the course.
     */
    public Course(String department, int ID, String name) {
        this.department = department;
        this.ID = ID;
        this.name = name;
    }

    /**
     * Gets the department of this {@code Course}.
     * @return {@code this.department}
     */
    public String getDepartment() {
        return this.department;
    }

    /**
     * Gets the overall course ID.
     * @return the department and ID for this {@code Course}.
     */
    public String getCourseID() {
        return this.department + this.ID;
    }

    /**
     * Converts this {@code Course} to a printable format.
     * @return this {@code Course} as a {@code String}.
     */
    public String toString() {
        return getCourseID() + " " + this.name;
    }
}
