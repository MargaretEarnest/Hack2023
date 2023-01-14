export class Student {
    private email: string;
    private prefix: string;
    private fName: string;
    private lName: string;
    private suffix: string;
    private type: number;
    private majors: string[];
    private yearOfGraduation: number;
    private gpa: number;
    private classes: string[];


    constructor(email: string, prefix: string, fName: string, lName: string, suffix: string, type: number, majors: string[], yearOfGraduation: number, gpa: number, classes: string[]) {
        this.email = email;
        this.prefix = prefix;
        this.fName = fName;
        this.lName = lName;
        this.suffix = suffix;
        this.type = type;
        this.majors = majors;
        this.yearOfGraduation = yearOfGraduation;
        this.gpa = gpa;
        this.classes = classes;
    }

    public generateJsonString(){
        return JSON.stringify(this);
    }
}
