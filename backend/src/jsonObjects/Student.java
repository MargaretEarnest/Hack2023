package jsonObjects;

import utils.HashList;
import utils.Vars;

/**
 * The {@code jsonObjects.Student} object stores all necessary information for which
 */
public class Student extends Person {
    private StudentType status;
    private final HashList<String> majors;
    private int yearOfGraduation;
    private float gpa;
    private final HashList<Course> classes;

    // Lists of jobs with different restrictions.
    private final HashList<Job>
        acceptedJobs,    // Jobs for which the student has been requested for review by an employer.
        requestedJobs,   // Jobs for which the student has requested consideration.
        hiredJobs;       // Jobs for which the student has been hired.

    /**
     * Creates a new {@code jsonObjects.Student} with a given name.
     * @param email the contact email for this {@code jsonObjects.Student}.
     * @param fName the first name of this {@code jsonObjects.Student}.
     * @param lName the last name of this {@code jsonObjects.Student}.
     * @param prefix the salutation for this {@code jsonObjects.Student}.
     * @param suffix the suffix for this {@code jsonObjects.Student}.
     * param university the employed {@code jsonObjects.University} for this {@code jsonObjects.Student}.
     */
    public Student(String email, String prefix, String fName, String lName,
                   String suffix, int status
                   //, String[] majors
                   , int yearOfGraduation
                   , float gpa
                   //, String[] classes
                   //, jsonObjects.University university
                   ) {
        super(fName, lName, prefix, suffix, email, Vars.WPI);
        this.status = StudentType.getType(status);
        //this.majors = new utils.HashList<>(majors);
        this.majors = new HashList<>();
        this.yearOfGraduation = yearOfGraduation;
        this.gpa = gpa;
        this.classes = new HashList<>();
        //for (String course : classes) {
        //    this.classes.add(utils.Vars.courses.get(course));
        //}
        this.acceptedJobs = new HashList<>();
        this.requestedJobs = new HashList<>();
        this.hiredJobs = new HashList<>();
    }

    /**
     * Changes the status of this {@code jsonObjects.Student}.
     * @param status the new status.
     * @return the old status.
     */
    public StudentType changeStatus(StudentType status) {
        final StudentType oldStatus = this.status;
        this.status = status;
        return oldStatus;
    }

    /**
     * Gets the status of this {@code jsonObjects.Student}.
     * @return {@code this.status}
     */
    public StudentType getStatus() {
        return this.status;
    }

    /**
     * Gets all majors associated with this {@code jsonObjects.Student}.
     * @return {@code this.majors}
     */
    public HashList<String> getMajors() {
        return this.majors;
    }

    /**
     * Changes the year of graduation for this {@code jsonObjects.Student}.
     * @param year the new year.
     * @return the old year.
     */
    public int changeYearOfGraduation(int year) {
        final int oldYear = this.yearOfGraduation;
        this.yearOfGraduation = year;
        return oldYear;
    }

    /**
     * Gets the year of graduation for this {@code jsonObjects.Student}.
     * @return {@code this.yearOfGraduation}
     */
    public int getYearOfGraduation() {
        return this.yearOfGraduation;
    }

    /**
     * Changes the GPA for this {@code jsonObjects.Student}.
     * @param gpa the new GPA.
     * @return the old GPA.
     */
    public float changeGPA(float gpa) {
        final float oldGPA = this.gpa;
        this.gpa = gpa;
        return oldGPA;
    }

    /**
     * Gets the grade point average for this {@code jsonObjects.Student}.
     * @return {@code this.gpa}
     */
    public float getGPA() {
        return this.gpa;
    }

    /**
     * Gets the list of {@code Courses} for this {@code jsonObjects.Student}.
     * @return {@code this.classes}
     */
    public HashList<Course> getClasses() {
        return this.classes;
    }

    /**
     * Gets the list of accepted jobs for this {@code jsonObjects.Student}.
     * @return {@code this.acceptedJobs}
     */
    public HashList<Job> getAcceptedJobs() {
        return this.acceptedJobs;
    }

    /**
     * Gets the list of requested jobs for this {@code jsonObjects.Student}.
     * @return {@code this.requestedJobs}
     */
    public HashList<Job> getRequestedJobs() {
        return this.requestedJobs;
    }

    /**
     * Gets the list of hired jobs for this {@code jsonObjects.Student}.
     * @return {@code this.hiredJobs}
     */
    public HashList<Job> getHiredJobs() {
        return this.hiredJobs;
    }
}