package jsonObjects;

public record JobListRequest(
        String email,
        int status,
        String[] majors,
        String[] departments,
        String[] locations,
        MinMax hours,
        MinMax teamSize,
        boolean federalWorkStudy
) {}
