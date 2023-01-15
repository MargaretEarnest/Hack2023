package jsonObjects;

public record ChooseStudentRequest(
        String studentEmail,
        String jobId,
        boolean accepted
) {}
