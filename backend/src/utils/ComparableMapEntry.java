package utils;

/**
 * Creates a wrapper object that allows a {@code Value} object type to be sorted into some
 * data structure.
 * @param key the {@code Comparable} key element compared to other keys to establish
 *            a natural ordering.
 * @param value the value associated with and "attached to" a specific key to move with it
 *              in any reordering.
 * @param <Key> the data type of the {@code Comparable key} element.
 * @param <Value> the data type of the stored element.
 */
public record ComparableMapEntry<Key extends Comparable<Key>, Value>(Key key, Value value)
        implements Comparable<ComparableMapEntry<Key, Value>> {
    /**
     * Creates a new {@code ComparableMapEntry}.
     * @param key   the Key item used to sort this ComparableMapEntry
     * @param value the mapped item
     */
    public ComparableMapEntry {
    }

    /**
     * Gets the Key for this ComparableMapEntry
     *
     * @return this.key
     */
    @Override
    public Key key() {
        return this.key;
    }

    /**
     * Gets the Value for this ComparableMapEntry
     *
     * @return this.value
     */
    @Override
    public Value value() {
        return value;
    }

    /**
     * Compares this ComparableMapEntry to another ComparableMapEntry
     *
     * @param o the comparator ComparableMapEntry
     * @return given the parameters
     * -1 if this Key is "less than" the 'o' Key
     * 0 if this Key is "equal to" the 'o' Key
     * 1 if this Key is "greater than" the 'o' Key
     */
    @Override
    public int compareTo(ComparableMapEntry<Key, Value> o) {
        return this.key.compareTo(o.key);
    }

    /**
     * Determines whether this ComparableMapEntry is equal to a specified Object
     *
     * @param o the comparator Object
     * @return true if the Object is a ComparableMapEntry with common key and value classes and objects, else false
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ComparableMapEntry<?, ?> convert)) {
            return false;
        }
        return this.key.equals(convert.key) && this.value.equals(convert.value);
    }

    /**
     * Provides the TrueText of this ComparableMapEntry
     *
     * @return this ComparableMapEntry in a parsable format
     */
    public String serialize() {
        return this.key + ">" + this.value;
    }

    /**
     * Converts this ComparableMapEntry to a printable format
     *
     * @return this ComparableMapEntry as a String
     */
    @Override
    public String toString() {
        return "[" + this.key + " -> " + this.value + "]";
    }
}
