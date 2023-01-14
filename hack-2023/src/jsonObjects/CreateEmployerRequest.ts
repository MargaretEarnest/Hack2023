import {Employer} from "./Employer";

export class CreateEmployerRequest {
    private employer: Employer;
    private password: string;

    constructor(employer: Employer, password: string) {
        this.employer = employer;
        this.password = password;
    }
}