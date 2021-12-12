package io.github.joblo2213.adventofcode.y2021.day12;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Cave {
    private final String label;
    private final List<Cave> linked = new ArrayList<>();

    public Cave(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public Stream<Cave> linked() {
        return linked.stream();
    }

    public void link(Cave cave) {
        linked.add(cave);
        cave.linked.add(this);
    }

    public boolean isSmall() {
        return label.toLowerCase().equals(label);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cave cave = (Cave) o;
        return Objects.equals(label, cave.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    @Override
    public String toString() {
        return label;
    }
}
