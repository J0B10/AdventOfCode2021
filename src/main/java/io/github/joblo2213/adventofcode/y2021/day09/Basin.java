package io.github.joblo2213.adventofcode.y2021.day09;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Basin {
    private final Pos center;
    private final Set<Pos> area = new HashSet<>();


    public Basin(int centerX, int centerY) {
        this.center = new Pos(centerX, centerY);
        area.add(center);
    }

    public Pos getCenter() {
        return center;
    }

    public int getSize() {
        return area.size();
    }

    public void growArea(int[][] heightmap) {
        List<Pos> newPos = area.stream().flatMap(pos ->
                Day09B.getNeighbours(pos.x(), pos.y(), heightmap)
                        .filter(n -> n.getValue(heightmap) != 9)
                        .filter(n -> !area.contains(n))
        ).toList();
        if (!newPos.isEmpty()) {
            area.addAll(newPos);
            growArea(heightmap);
        }
    }

    public boolean overlap(Basin other) {
        return area.contains(other.center);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basin basin = (Basin) o;
        return Objects.equals(center, basin.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(center);
    }
}
