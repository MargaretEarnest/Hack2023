import {MinMax} from "./MinMax";

export class JobListRequest{
    private email: string;
    private status: string[];
    private majors: string[];
    private departments: string[]
    private locations: string[]
    private hours: MinMax;
    private teamSize: MinMax;
    private federalWorkStudy: boolean;

    constructor(email: string, status: string[], majors: string[], departments: string[], locations: string[], hours: MinMax, teamSize: MinMax, federalWorkStudy: boolean) {
        this.email = email;
        this.status = status;
        this.majors = majors;
        this.departments = departments;
        this.locations = locations;
        this.hours = hours;
        this.teamSize = teamSize;
        this.federalWorkStudy = federalWorkStudy;
    }
}