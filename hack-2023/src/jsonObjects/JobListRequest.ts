import {MinMax} from "./MinMax";

export class JobListRequest{
    private email: string;
    private status: number;
    private majors: string[];
    private departments: string[]
    private locations: string[]
    private hours: MinMax;
    private teamSize: MinMax;

    constructor(email: string, status: number, majors: string[], departments: string[], locations: string[], hours: MinMax, teamSize: MinMax) {
        this.email = email;
        this.status = status;
        this.majors = majors;
        this.departments = departments;
        this.locations = locations;
        this.hours = hours;
        this.teamSize = teamSize;
    }
}