package io.github.joblo2213.adventofcode.y2019.day10;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("DuplicatedCode")
public class Day10A extends Task {

    static record Result(Asteroid center, int visible) {}

    @Override
    public long run(PuzzleInput input) {
        return findCenter(input).visible();
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public Result findCenter(PuzzleInput input) {
        List<Asteroid> asteroids = parseAsteroids(input);
        return asteroids.parallelStream().map(center -> {
            HashMap<Double, Asteroid> visible = new HashMap<>();
            //sort by distance (visible will always be before invisible)
            asteroids.stream().sorted(Comparator.comparingInt(center::distanceSquared))
                    .skip(1) //skip the center itself
                    .forEachOrdered(asteroid -> {
                        double angle = center.angle(asteroid);
                        if (!visible.containsKey(angle)) visible.put(angle, asteroid);
                    });
            return new Result(center, visible.size());
        }).max(Comparator.comparingInt(Result::visible)).get();
    }

    public List<Asteroid> parseAsteroids(PuzzleInput input) {
        int height = input.getLines().size(), width = input.getLines().get(0).length();
        List<Asteroid> asteroids = new ArrayList<>(height * width);
        int y = 0;
        for (String line : input) {
            int x = 0;
            for (char c : line.toCharArray()) {
                if (c == '#') asteroids.add(new Asteroid(x, y));
                x++;
            }
            y++;
        }
        return asteroids;
    }
}
