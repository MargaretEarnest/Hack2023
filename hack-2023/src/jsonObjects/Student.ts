export class Student {
    public email: string;
    public prefix: string;
    public fName: string;
    public lName: string;
    public suffix: string;
    public status: number;
    public majors: string[];
    public yearOfGraduation: number;
    public gpa: number;
    public classes: string[];


    constructor(email: string, prefix: string, fName: string, lName: string, suffix: string, status: number, majors: string[], yearOfGraduation: number, gpa: number, classes: string[]) {
        this.email = email;
        this.prefix = prefix;
        this.fName = fName;
        this.lName = lName;
        this.suffix = suffix;
        this.status = status;
        this.majors = majors;
        this.yearOfGraduation = yearOfGraduation;
        this.gpa = gpa;
        this.classes = classes;
    }
}