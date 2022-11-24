package org.itstep;

public enum Category {
    A(1, "A"),
    B(2, "B"),
    C(3, "C"),
    D(4, "D"),
    E(5, "E"),
    NONE(6, "None");
    private final int number;
    private final String mean;

    private Category(int number, String mean) {
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
        return String.format("%1s", mean);
    }
}



