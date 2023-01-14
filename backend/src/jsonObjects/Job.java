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
    int id; // unique id for this job
    HashList<Employer> creators; // employers overseeing the job
    int type; // 0=research, 1=TA, 2=other
    HashList<String> majors; // majors required for student
    String title; // name of the job position
    String department; // department name
    String location; // name of the location
    int numStudents; // number of students wanted to hire
    int hours; // hours per week
    String email; // contact email
    boolean federalWorkStudy; // if it counts for federal work study
    String desc; // description of the job position
    float gpa; // required GPA to apply
    int classYear; // required class year
    int grad; // 0 = undergrad, 1 = grad, 2 = doctoral student, 3 = postdoc
    String phone; // contact phone
    String contact; // contact person
    HashList<Student> employees; // list of employees

    public Job() {
//        creator = ;
//        type = ;
//        majors.add();
//        title = ;
//        desc = ;
//        gpa = ;
//        classYear = ;
//        email = ;

    }


}
