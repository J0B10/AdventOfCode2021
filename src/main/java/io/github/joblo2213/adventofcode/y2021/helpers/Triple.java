package io.github.joblo2213.adventofcode.y2021.helpers;

public record Triple<T, U, V>(T first, U second, V third) {

    @Override
    public String toString() {
        return "["+ first + ", " + second + ", " + third + "]";
    }
}