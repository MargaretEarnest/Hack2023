export class Employer {
    private email: string;
    private prefix: string;
    private fName: string;
    private lName: string;
    private suffix: string;
    private majors: string[];

    constructor(email: string, prefix: string, fName: string, lName: string, suffix: string, status: number, majors: string[]) {
        this.email = email;
        this.prefix = prefix;
        this.fName = fName;
        this.lName = lName;
        this.suffix = suffix;
        this.majors = majors;
    }
}