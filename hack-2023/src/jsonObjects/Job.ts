export class Job{
    private id: number;
    private creators: string[];
    private type: number;
    private majors: string[];
    private title: string;
    private department: string;
    private location: string;
    private numStudents: number;
    private hours: number;
    private email: string;
    private federalWorkStudy: boolean;
    private desc: string;
    private gpa: number;
    private classYear: number;
    private grad: number;
    private phone: string;
    private contact: string;
    private employees: string[];

    constructor(id: number, creators: string[], type: number, majors: string[], title: string, department: string, location: string, numStudents: number, hours: number, email: string, federalWorkStudy: boolean, desc: string, gpa: number, classYear: number, grad: number, phone: string, contact: string, employees: string[]) {
        this.id = id;
        this.creators = creators;
        this.type = type;
        this.majors = majors;
        this.title = title;
        this.department = department;
        this.location = location;
        this.numStudents = numStudents;
        this.hours = hours;
        this.email = email;
        this.federalWorkStudy = federalWorkStudy;
        this.desc = desc;
        this.gpa = gpa;
        this.classYear = classYear;
        this.grad = grad;
        this.classYear = classYear;
        this.phone = phone;
        this.contact = contact;
        this.employees = employees;
    }

}