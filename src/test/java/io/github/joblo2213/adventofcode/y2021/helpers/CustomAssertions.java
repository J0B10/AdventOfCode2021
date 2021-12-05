package io.github.joblo2213.adventofcode.y2021.helpers;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.fail;

public class CustomAssertions {

    public static void assertContains(Collection<?> expected, Collection<?> actual) {
        if (expected == null) fail("expected collection was <null>");
        for (Object o : expected) assertContains(o, actual);
    }

    public static void assertContains(Object expected, Collection<?> actual) {
        if (expected == null) fail("expected object was <null>");
        if (actual == null) fail("actual collection was <null>");
        if (!actual.contains(expected)) fail("collection does not contain <" + expected + ">");
    }

    public static void assertContainsOnly(Collection<?> expected, Collection<?> actual) {
        assertContains(expected, actual);
        switch (actual.size() - expected.size()) {
            case 0 -> {
            }
            case 1 -> {
                if (actual.size() < 1000000) {
                    Object additional = actual.stream().filter(e -> !expected.contains(e)).findAny().orElseThrow();
                    fail("collection contains additional element: <" + additional + ">");
                } else {
                    fail("collection contains 1 additional element");
                }
            }
            default -> fail("collection contains " + (actual.size() - expected.size()) + " additional elements");
        }
    }
}
