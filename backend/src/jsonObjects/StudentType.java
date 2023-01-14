package jsonObjects;

/**
 * Stores different types of students that can be employed at a {@code jsonObjects.University}.
 */
public enum StudentType {
    UNDERGRADUATE(0),
    GRADUATE(1),
    DOCTORAL_STUDENT(2),
    POST_DOCTORATE(3);

    // Stores the int value associated with this jsonObjects.StudentType.
    private final int value;

    /**
     * Creates a new {@code jsonObjects.StudentType}.
     * @param value the int value used to distinguish different types of {@code jsonObjects.Student}.
     */
    StudentType(int value) {
        this.value = value;
    }

    /**
     * Gets the int equivalent of this {@code jsonObjects.StudentType}.
     * @return {@code this.value}
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Gets the {@code jsonObjects.StudentType} associated with a specified int value.
     * @param value the specified int.
     * @return the target {@code jsonObjects.StudentType}.
     * @throws IllegalArgumentException if the given int is outside the specified bounds
     * for representation of a {@code jsonObjects.StudentType}.
     */
    public static StudentType getType(int value) throws IllegalArgumentException {
        return switch (value) {
            case 0 -> UNDERGRADUATE;
            case 1 -> GRADUATE;
            case 2 -> DOCTORAL_STUDENT;
            case 3 -> POST_DOCTORATE;
            default -> throw new IllegalArgumentException();
        };
    }
}