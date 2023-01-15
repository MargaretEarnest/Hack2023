export class ChooseStudentRequest {
    private studentEmail: string;
    private jobId: string;
    private accepted: boolean;

    constructor(studentEmail: string, jobId: string, accepted: boolean) {
        this.studentEmail = studentEmail;
        this.accepted = accepted;
        this.jobId = jobId;
    }
}