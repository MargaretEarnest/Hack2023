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
     * Computes a difficulty for this course.
     * @return the relative difficulty of this class on a scale of 1 to 5
     */
    public int difficulty() {
        return this.ID / (this.ID >= 1000 ? 1000 : 100);
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
            System.out.println(s);
            String[] split = s.split(" ");
            if (split.length == 3) {
                list.add(new Course(split[0], Integer.parseInt(split[1]), split[2]));
            } else if(split.length == 2) {
                list.add(new Course(split[0], Integer.parseInt(split[1]), ""));
            } else {
                int di = 0;
                while(Character.isLetter(s.charAt(di))) {
                    di++;
                }
                list.add(new Course(split[0].substring(0, di),
                        Integer.parseInt(split[0].substring(di)), ""));
            }
        }
        return list;
    }
}
