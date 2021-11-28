package io.github.joblo2213.adventofcode.y2021.helpers;

@SuppressWarnings("MethodNameSameAsClassName")
public class TODO extends RuntimeException {

    public TODO(String message) {
        super("TODO: " + message);
    }

    public TODO() {
        this("TODO");
    }

    public static <E> E TODO() {
        throw new TODO();
    }

    public static <E> E TODO(String message) {
        throw new TODO(message);
    }
}
