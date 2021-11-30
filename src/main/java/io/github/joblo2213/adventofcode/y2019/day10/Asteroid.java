package io.github.joblo2213.adventofcode.y2019.day10;

public record Asteroid(int x, int y) {

    public int distanceSquared(Asteroid other) {
        int dx = Math.abs(other.x - x), dy = Math.abs(other.y - y);
        return dx * dx + dy * dy;
    }

    public double angle(Asteroid other) {
        int dx = other.x - x, dy = other.y - y;
        double angle = Math.atan((double) dy / dx);
        if (dx < 0) return angle + Math.PI;
        else return angle;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
