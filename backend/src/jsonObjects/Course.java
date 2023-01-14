package jsonObjects;

import utils.HashList;

public class Course {
    // The department and course name.
    private final String department, name;
    // The integer ID for this course.
    private final int ID;

    /**
     * Creates a new {@code jsonObjects.Course}.
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
     * Gets the department of this {@code jsonObjects.Course}.
     * @return {@code this.department}
     */
    public String getDepartment() {
        return this.department;
    }

    /**
     * Gets the overall course ID.
     * @return the department and ID for this {@code jsonObjects.Course}.
     */
    public String getCourseID() {
        return this.department + this.ID;
    }

    /**
     * Gets the hashCode for this {@code Course}
     * @return
     */
    @Override
    public int hashCode() {
        return getCourseID().hashCode();
    }

    /**
     * Converts this {@code jsonObjects.Course} to a printable format.
     * @return this {@code jsonObjects.Course} as a {@code String}.
     */
    public String toString() {
        return getCourseID() + " " + this.name;
    }

    // static methods

    /**
     * Parses an iterable list of {@code Strings} into a {@code HashList} of {@code Courses}.
     * @param str the target {@code String}.
     * @return the generated {@code HashList}.
     */
    public static HashList<Course> parse(Iterable<String> str) {
        HashList<Course> list = new HashList<>();
        for(String s : str) {
            final int spaceIndex = s.indexOf(' ');
            int departmentIndex = 0;
            while(Character.isLetter(s.charAt(departmentIndex))) {
                departmentIndex++;
            }
            list.add(new Course(s.substring(0, departmentIndex),
                    Integer.parseInt(s.substring(departmentIndex, spaceIndex)),
                    s.substring(spaceIndex + 1)));
        }
        return list;
    }
}
