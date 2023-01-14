public class Employer extends Person {
    /**
     * Creates a new {@code Employer}.
     * @param fName the first name of this {@code Employer}.
     * @param lName the last name of this {@code Employer}.
     * @param prefix the salutation for this {@code Employer}.
     * @param suffix the suffix for this {@code Employer}.
     * @param email the contact email for this {@code Employer}.
     * @param university the employed {@code University} for this {@code Employer}.
     */
    public Employer(String fName, String lName, String prefix, String suffix, String email,
                    University university) {
        super(fName, lName, prefix, suffix, email, university);
    }
}
