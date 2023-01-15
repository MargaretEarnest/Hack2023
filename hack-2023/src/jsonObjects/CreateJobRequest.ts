export class CreateJobRequest {
    public ID: string;
    public title: string;
    public department: string;
    public location: string;
    public numStudents: number;
    public hours: number;
    public email: string;
    public federalWorkStudy: boolean;
    public desc: string;
    public requirements: string[];
    public phone: string;
    public contact: string;
    public status: number;

    constructor(ID: string, title: string, department: string, location: string,
                numStudents: number, hours: number, email: string, federalWorkStudy: boolean,
                desc: string, requirements: string[], phone: string, contact: string,
                status: number) {
        this.ID = ID;
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
}