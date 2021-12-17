package io.github.joblo2213.adventofcode.y2021.day17;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Probe {

    record Vector(int x, int y) {
    }

    private Vector pos;

    private Vector velocity;

    private final List<Vector> path = new ArrayList<>();

    public Probe(int x, int y, int vx, int vy) {
        this.pos = new Vector(x, y);
        this.velocity = new Vector(vx, vy);
        this.path.add(pos);
    }

    public Probe(int vx, int vy) {
        this(0, 0, vx, vy);
    }

    public Vector getPos() {
        return pos;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void step() {
        pos = new Vector(pos.x() + velocity.x(), pos.y() + velocity.y());
        path.add(pos);
        int vx = velocity.x();
        if (vx > 0) vx--;
        else if (vx < 0) vx++;
        velocity = new Vector(vx, velocity.y() - 1);
    }

    public Stream<Vector> path() {
        return path.stream();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Probe probe = (Probe) o;
        return Objects.equals(pos, probe.pos) && Objects.equals(velocity, probe.velocity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos, velocity);
    }

    @Override
    public String toString() {
        return "Probe{" + "P=" + pos + ", v=" + velocity + '}';
    }
}
