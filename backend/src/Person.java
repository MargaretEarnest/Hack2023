public class Person {
    // The first name, last name, prefix, and suffix of this person.
    private String fName, lName, prefix, suffix;
    // The contact email for this person.
    private String email;
    // The associated university for this person.
    private final University university;

    /**
     * Creates a new {@code Person}.
     * @param fName the first name of this {@code Person}.
     * @param lName the last name of this {@code Person}.
     * @param prefix the salutation for this {@code Person}.
     * @param suffix the suffix for this {@code Person}.
     * @param email the contact email for this {@code Person}.
     * @param university the employed {@code University} for this {@code Person}.
     */
    public Person(String fName, String lName, String prefix, String suffix, String email,
                  University university) {
        changeName(prefix, fName, lName, suffix);
        changeEmail(email);
        this.university = university;
    }

    /**
     * Changes the name associated with this {@code Person}.
     * @param prefix the new prefix
     * @param fName the new first name
     * @param lName the new last name
     * @param suffix the new suffix
     * @return the old name
     */
    public String[] changeName(String prefix, String fName, String lName, String suffix) {
        final String[] oldName = {this.prefix, this.fName, this.lName, this.suffix};
        this.prefix = prefix;
        this.fName = fName;
        this.lName = lName;
        this.suffix = suffix;
        return oldName;
    }

    /**
     * Changes the email associated with this {@code Person}.
     * @param newEmail the new email address.
     * @return the previous email address.
     */
    public String changeEmail(String newEmail) {
        final String oldEmail = this.email;
        this.email = newEmail;
        return oldEmail;
    }

    /**
     * Gets the email associated with this {@code Person}.
     * @return {@code this.email}
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Gets the {@code University} associated with this {@code Person}.
     * @return {@code this.university}
     */
    public University getUniversity() {
        return this.university;
    }

    /**
     * Converts this {@code Person} to a printable format.
     * @return this {@code Person} as a {@code String}.
     */
    @Override
    public String toString() {
        return this.prefix.concat(" ").concat(this.fName).concat( " ")
                .concat(this.lName).concat(" ").concat(this.suffix);
    }
}
