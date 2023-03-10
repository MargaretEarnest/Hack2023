export class Job{
    public id: number;
    public creators: string[];
    public type: number;
    public majors: string[];
    public title: string;
    public department: string;
    public location: string;
    public numStudents: number;
    public hours: number;
    public email: string;
    public federalWorkStudy: boolean;
    public desc: string;
    public gpa: number;
    public classYear: number;
    public phone: string;
    public contact: string;
    public employees: string[];

    constructor(id: number, creators: string[], type: number, majors: string[], title: string, department: string, location: string, numStudents: number, hours: number, email: string, federalWorkStudy: boolean, desc: string, gpa: number, classYear: number, phone: string, contact: string, employees: string[]) {
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
        this.classYear = classYear;
        this.phone = phone;
        this.contact = contact;
        this.employees = employees;
    }

}