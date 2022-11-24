package org.itstep;

public enum Condition {
    ACTIVE(1, "Active"),
    ARCHIVE(2, "Archive");

    private final int number;
    private final String mean;

    private Condition(int number, String mean) {
        this.number = number;
        this.mean = mean;
    }

    String mean() {
        return this.mean;
    }

    int number() {
        return this.number;
    }

    @Override
    public String toString() {
        return String.format("%s", mean);
    }
}
