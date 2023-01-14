package jsonObjects;

public class Employer extends Person {
    /**
     * Creates a new {@code jsonObjects.Employer}.
     * @param fName the first name of this {@code jsonObjects.Employer}.
     * @param lName the last name of this {@code jsonObjects.Employer}.
     * @param prefix the salutation for this {@code jsonObjects.Employer}.
     * @param suffix the suffix for this {@code jsonObjects.Employer}.
     * @param email the contact email for this {@code jsonObjects.Employer}.
     * @param university the employed {@code jsonObjects.University} for this {@code jsonObjects.Employer}.
     */
    public Employer(String fName, String lName, String prefix, String suffix, String email,
                    University university) {
        super(fName, lName, prefix, suffix, email, university);
    }

    public Employer(String fname, String lname, String pname, String sname, String email, String university) {
        super(fname, lname, pname, sname, email,null );
        //TODO Fix university
    }
}
