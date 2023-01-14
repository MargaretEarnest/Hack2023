package utils;

import jsonObjects.Course;
import jsonObjects.University;
import utils.HashList;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores global variables we use.
 */
public class Vars {
    public static final HashList<String> majors = new HashList<>();
    public static University WPI = new University("WPI");
    public static final Map<String, Course> courses = new HashMap<>();
}
