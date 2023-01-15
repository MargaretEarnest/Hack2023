package utils;

public record Index<A extends Comparable<A>, B extends Comparable<B>>(A a, B b)
        implements Comparable<Index<A, B>> {
    /**
     * Creates a new Index
     *
     * @param a the primary Comparable Object
     * @param b the secondary Comparable Object
     */
    public Index {
    }

    /**
     * Gets the primary value of this Index
     *
     * @return this.prime
     */
    @Override
    public A a() {
        return this.a;
    }

    /**
     * Gets the secondary value of this Index
     *
     * @return this.second
     */
    @Override
    public B b() {
        return this.b;
    }

    /**
     * Gets the hashCode of this Index
     *
     * @return the unique hashCode identifier
     */
    @Override
    public int hashCode() {
        int a = this.a.hashCode() << 1, b = this.b.hashCode() << 1, aAbs = a;
        if (a < 0) {
            aAbs = 1 - aAbs;
        }
        if (b < 0) {
            b = 1 - b;
        }
        return (aAbs + b + 1) * (aAbs + b) + a;
    }

    /**
     * Compares this Index to another Index
     *
     * @param o the comparator Object
     * @return given the parameters
     * -1 if this Object is "less than" o
     * 0 if this Object is "equal to" o
     * 1 if this Object is "greater than" o
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified Object's type prevents it from being compared to this Object
     */
    @Override
    public int compareTo(Index<A, B> o) {
        int compare = this.a.compareTo(o.a);
        return (compare == 0) ? this.b.compareTo(o.b) : compare;
    }

    /**
     * Determines whether this Index is equal to a specified Object
     *
     * @param o the comparator Object
     * @return true if the Object is an Index with equal primary and secondary comparators, else false
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Index<?, ?> convert)) {
            return false;
        }
        return this.a.equals(convert.a) && this.b.equals(convert.b);
    }

    /**
     * Provides the TrueText of this Index
     *
     * @return this Index in a parsable format
     */
    public String trueText() {
        return toString();
    }

    /**
     * Converts this Index to a printable format
     *
     * @return this Index as a String
     */
    @Override
    public String toString() {
        return this.a + "|" + this.b;
    }
}
