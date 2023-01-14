import {Student} from "./Student";

export class CreateStudentRequest{
    private student: Student;
    private password: string;

    constructor(student: Student, password: string) {
        this.student = student;
        this.password = password;
    }
}