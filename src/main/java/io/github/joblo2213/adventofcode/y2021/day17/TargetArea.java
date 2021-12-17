package io.github.joblo2213.adventofcode.y2021.day17;

import io.github.joblo2213.adventofcode.y2021.day17.Probe.Vector;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record TargetArea(int xmin, int ymin, int xmax, int ymax) {

    private static final Pattern REGEX = Pattern.compile("""
            target area: x=(-?\\d+)\\.\\.(-?\\d+), y=(-?\\d+)\\.\\.(-?\\d+)""");

    public static TargetArea parse(String s) {
        Matcher m = REGEX.matcher(s);
        if (!m.matches()) throw new IllegalArgumentException(s);
        return new TargetArea(
                Integer.parseInt(m.group(1)),
                Integer.parseInt(m.group(3)),
                Integer.parseInt(m.group(2)),
                Integer.parseInt(m.group(4))
        );
    }

    public boolean contains(Vector pos) {
        return (xmin <= pos.x() && pos.x() <= xmax)
                && (ymin <= pos.y() && pos.y() <= ymax);
    }

    public boolean hits(Probe probe) {
        do probe.step();
        while (!contains(probe.getPos()) && probe.getPos().y() >= ymin);
        return contains(probe.getPos());
    }
}
