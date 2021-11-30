package io.github.joblo2213.adventofcode.y2021.helpers;

public record Tuple<T, U>(T first, U second) {

    @Override
    public String toString() {
        return "["+ first + ", " + second + "]";
    }
}
