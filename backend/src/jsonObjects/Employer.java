package jsonObjects;

import utils.HashList;

public class Employer extends Person {
    // A list of all jobs offered by this employer.
    private final HashList<Job> jobs;

    /**
     * Creates a new {@code jsonObjects.Employer}.
     * @param fName the first name of this {@code jsonObjects.Employer}.
     * @param lName the last name of this {@code jsonObjects.Employer}.
     * @param prefix the salutation for this {@code jsonObjects.Employer}.
     * @param suffix the suffix for this {@code jsonObjects.Employer}.
     * @param email the contact email for this {@code jsonObjects.Employer}.
     * @param university the employed {@code jsonObjects.University} for this {@code jsonObjects.Employer}.
     */
    public Employer(String fName, String lName, String prefix, String suffix, String email,
                    University university) {
        super(fName, lName, prefix, suffix, email, university);
        this.jobs = new HashList<>();
    }

    /**
     * Gets a list of all {@code Jobs} offered by this {@code Employer}.
     * @return the list of {@code Jobs}.
     */
    public HashList<Job> getJobs() {
        return this.jobs;
    }

    /**
     * Finds a list of {@code Students} eligible for a specific {@code Job}.
     * @param job the target {@code Job}.
     * @return the list of {@code Students}.
     */
    public HashList<Student> getEligibleStudents(Job job) {
        final HashList<Course> requiredCourses = job.getRequirements();
        final HashList<Student> students = new HashList<>();
        for(Student student : super.getUniversity().getStudents()) {
            HashList<Course> studentCourses = student.getClasses();
            boolean acceptStudent = true;
            for(Course requirement : requiredCourses) {
                if(acceptStudent && ! studentCourses.contains(requirement)) {
                    acceptStudent = false;
                }
            }
            if(acceptStudent) {
                students.add(student);
            }
        }
        return students;
    }
}
