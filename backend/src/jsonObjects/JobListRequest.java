package jsonObjects;

public record JobListRequest(
        String email,
        String[] status,
        String[] majors,
        String[] departments,
        String[] locations,
        MinMax hours,
        MinMax teamSize
) {}
