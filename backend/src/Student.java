/**
 * The {@code Student} object stores all necessary information for which
 */
public class Student extends Person {
    private StudentType status;
    private HashList<String> majors;
    private int yearOfGraduation;
    private float gpa;
    private HashList<Course> classes;

    // Lists of jobs with different restrictions.
    private final HashList<Job>
        acceptedJobs,    // Jobs for which the student has been requested for review by an employer.
        requestedJobs,   // Jobs for which the student has requested consideration.
        hiredJobs;       // Jobs for which the student has been hired.
    
    /**
     * Creates a new {@code Student} with a given name.
     * @param email the contact email for this {@code Student}.
     * @param fName the first name of this {@code Student}.
     * @param lName the last name of this {@code Student}.
     * @param prefix the salutation for this {@code Student}.
     * @param suffix the suffix for this {@code Student}.
     * @param university the employed {@code University} for this {@code Student}.
     */
    public Student(String email, String prefix, String fName, String lName,
                   String suffix, int status, String[] majors, int yearOfGraduation,
                           float gpa, String[] classes, University university) {
        super(fName, lName, prefix, suffix, email, university);
        this.status = StudentType.getType(status);
        this.majors = new HashList<String>(majors);
        this.yearOfGraduation = yearOfGraduation;
        this.gpa = gpa;
        this.classes = new HashList<>();
        for (String course : classes) {
            this.classes.add(Vars.courses.get(course));
        }
        this.acceptedJobs = new HashList<>();
        this.requestedJobs = new HashList<>();
        this.hiredJobs = new HashList<>();
    }

    /**
     * Gets the list of accepted jobs for this {@code Student}.
     * @return {@code this.acceptedJobs}
     */
    public HashList<Job> getAcceptedJobs() {
        return this.acceptedJobs;
    }

    /**
     * Gets the list of requested jobs for this {@code Student}.
     * @return {@code this.requestedJobs}
     */
    public HashList<Job> getRequestedJobs() {
        return this.requestedJobs;
    }

    /**
     * Gets the list of hired jobs for this {@code Student}.
     * @return {@code this.hiredJobs}
     */
    public HashList<Job> getHiredJobs() {
        return this.hiredJobs;
    }
}
