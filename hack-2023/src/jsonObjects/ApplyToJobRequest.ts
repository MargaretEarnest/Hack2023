export class ApplyToJobRequest{
    private jobName: string;
    private email: string;

    constructor(jobName: string, email: string) {
        this.jobName = jobName;
        this.email = email;
    }
}