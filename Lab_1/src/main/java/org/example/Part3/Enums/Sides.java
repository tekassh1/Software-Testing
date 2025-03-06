package org.example.Part3.Enums;

public enum Sides {
    LEFT("левая"),
    RIGHT("правая");

    private final String message;

    Sides(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
