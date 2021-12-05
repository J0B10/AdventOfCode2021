package io.github.joblo2213.adventofcode.y2021.day05;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static io.github.joblo2213.adventofcode.y2021.day05.Line.Orientation.*;


public record Line(Pos start, Pos end) {

    enum Orientation {
        HORIZONTAL,
        VERTICAL,
        DIAGONAL
    }

    private static final Pattern REGEX = Pattern.compile("(\\d+),(\\d+) -> (\\d+),(\\d+)");

    public static Line parse(String s) {
        Matcher m = REGEX.matcher(s);
        if (!m.matches()) throw new IllegalArgumentException("invalid instruction '" + s + "'");
        return new Line(
                new Pos(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2))),
                new Pos(Integer.parseInt(m.group(3)), Integer.parseInt(m.group(4)))
        );
    }

    public Orientation getOrientation() {
        if (start.x() == end.x()) return VERTICAL;
        else if (start.y() == end.y()) return HORIZONTAL;
        else return DIAGONAL;
    }

    public Stream<Pos> positions() {
        final int xmin = Math.min(start.x(), end.x());
        final int ymin = Math.min(start.y(), end.y());
        final int length = switch (getOrientation()) {
            case HORIZONTAL, DIAGONAL -> Math.abs(end.x() - start.x());
            case VERTICAL -> Math.abs(end.y() - start.y());
        };
        IntStream ints = IntStream.rangeClosed(0, length);
        return switch (getOrientation()) {
            case HORIZONTAL -> ints.mapToObj(i -> new Pos(xmin + i, ymin));
            case VERTICAL -> ints.mapToObj(i -> new Pos(xmin, ymin + i));
            case DIAGONAL -> ints.mapToObj(i -> new Pos(
                    xmin + (start.x() == xmin ? i : length - i),
                    ymin + (start.y() == ymin ? i : length - i))
            );
        };
    }

    @Override
    public String toString() {
        return start + " ->" + end;
    }

    /**
     * @deprecated there is unknown behaviour when using this functionality with the puzzle input,
     * so don't use it for now.
     * Only works for horizontal & vertical lines.
     */
    @Deprecated
    public Stream<Pos> intersections(Line other) {
        if (getOrientation() == VERTICAL && other.getOrientation() == HORIZONTAL) {
            return Stream.of(new Pos(start.x(), other.start.y())).filter(this::belongsTo).filter(other::belongsTo);
        } else if (getOrientation() == HORIZONTAL && other.getOrientation() == VERTICAL) {
            return Stream.of(new Pos(other.start.x(), start.y())).filter(this::belongsTo).filter(other::belongsTo);
        } else if (getOrientation() == other.getOrientation()) {
            return positions().filter(other::belongsTo);
        } else {
            return Stream.empty();
        }
    }

    /**
     * @deprecated there is unknown behaviour when using this functionality with the puzzle input,
     * so don't use it for now.
     * Only works for horizontal & vertical lines.
     */
    @Deprecated
    public boolean belongsTo(Pos p) {
        int dx = end.x() - start.x();
        int dy = end.y() - start.y();
        int dx_pos = p.x() - start.x();
        int dy_pos = p.y() - start.y();
        return ((dx >= 0) == (dx_pos >= 0) && Math.abs(dx_pos) <= Math.abs(dx))
                && ((dy >= 0) == (dy_pos >= 0) && Math.abs(dy_pos) <= Math.abs(dy));
    }
}
