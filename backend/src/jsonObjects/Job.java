package jsonObjects;/*
Class contains a constructor to create a job as well as
specific job attributes including:
-creator
-type: research, TA, other
-major(s)
-title
-description
-GPA requirement
-class year(s) requirement
 */

import utils.HashList;

public class Job {
    String id; // unique id for this job
    String title; // name of the job position
    String department; // department name
    String location; // name of the location
    int numStudents; // number of students wanted to hire
    int hours; // hours per week
    String email; // contact email
    boolean federalWorkStudy; // if it counts for federal work study
    String desc; // description of the job position
    String requirements; // required GPA to apply
    String phone; // contact phone
    String contact; // contact person

    public Job(String id, String title, String department, String location, int numStudents, int hours, String email, boolean federalWorkStudy, String desc, String requirements, String phone, String contact) {
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
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumStudents() {
        return numStudents;
    }

    public void setNumStudents(int numStudents) {
        this.numStudents = numStudents;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isFederalWorkStudy() {
        return federalWorkStudy;
    }

    public void setFederalWorkStudy(boolean federalWorkStudy) {
        this.federalWorkStudy = federalWorkStudy;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
