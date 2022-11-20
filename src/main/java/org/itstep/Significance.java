package org.itstep;

public enum Significance {
    ONE(1, "1"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5");

    private final int number;
    private final String mean;

    private Significance(int number, String mean){
        this.number = number;
        this.mean = mean;
    }

    String mean (){
        return this.mean;
    }

    int number (){
        return this.number;
    }

    @Override
    public String toString() {
        return String.format("%1s", mean);
    }

}


