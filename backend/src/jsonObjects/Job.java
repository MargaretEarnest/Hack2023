package jsonObjects;

import utils.HashList;

/**
 * Stores all information needed to designate a {@code Job} at a {@code University}. This object
 * assigns to a task the following:
 * <ul>
 *     <li>A unique ID.</li>
 *     <li>A display title.</li>
 *     <li>An assigned academic department.</li>
 *     <li>A location of origin for the project.</li>
 *     <li>A desired number of students to complete the project.</li>
 *     <li>A desired weekly hour requirement.</li>
 *     <li>A contact email.</li>
 *     <li>A clarification on whether it counts for federal work study.</li>
 *     <li>A general description.</li>
 *     <li>A list of courses required for understanding the material in the project.</li>
 *     <li>A contact phone number.</li>
 *     <li>The name of the person to contact.</li>
 * </ul>
 */
public class Job {
    private String id; // unique id for this job
    private String title; // name of the job position
    private String department; // department name
    private String location; // name of the location
    private int numStudents; // number of students wanted to hire
    private int hours; // hours per week
    private String email; // contact email
    private boolean federalWorkStudy; // if it counts for federal work study
    private String desc; // description of the job position
    private final HashList<Course> requirements; // required courses
    private String phone; // contact phone
    private String contact; // contact person
    private int status; // type of student

    /**
     * Creates a new {@code Job} offering.
     * @param id the unique {@code Job} ID.
     * @param title the title of the {@code Job}.
     * @param department the department spearheading the {@code Job} opportunity.
     * @param location the location of the {@code Job}.
     * @param numStudents the number of students required for the {@code Job}.
     * @param hours the number of hours per week required for the {@code Job}.
     * @param email the contact email for the {@code Job}.
     * @param federalWorkStudy {@code true} if this {@code Job} provides credit toward federal
     *                                     work study, else {@code false}.
     * @param desc a general description of the {@code Job}.
     * @param requirements a list of all required {@code Courses} for this {@code Job}.
     * @param phone a contact phone number.
     * @param contact name of the {@code Employer} overseeing this {@code Job}.
     */
    public Job(String id, String title, String department, String location, int numStudents,
               int hours, String email, boolean federalWorkStudy, String desc,
               HashList<Course> requirements, String phone, String contact, int status) {
        this.id = id;
        this.title = title;
        this.department = department;
        this.location = location;
        this.numStudents = numStudents;
        this.hours = hours;
        this.email = email;
        this.federalWorkStudy = federalWorkStudy;
        this.desc = desc;
        this.requirements = requirements;
        this.phone = phone;
        this.contact = contact;
        this.status = status;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumStudents() {
        return this.numStudents;
    }

    public void setNumStudents(int numStudents) {
        this.numStudents = numStudents;
    }

    public int getHours() {
        return this.hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isFederalWorkStudy() {
        return this.federalWorkStudy;
    }

    public void setFederalWorkStudy(boolean federalWorkStudy) {
        this.federalWorkStudy = federalWorkStudy;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public HashList<Course> getRequirements() {
        return this.requirements;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getStatus() {
        return this.status;
    }

    /**
     * Gets the hashCode for this {@code Job}.
     * @return the hashCode for the {@code id} for this {@code Job}.
     */
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
