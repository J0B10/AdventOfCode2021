package io.github.joblo2213.adventofcode.y2019.day10;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("DuplicatedCode")
public class Day10B extends Task {

    private final Asteroid center;
    private List<Asteroid> asteroids;

    public Day10B(Asteroid center) {
        this.center = center;
    }

    @Override
    public long run(PuzzleInput input) {
        parseAsteroids(input);
        Queue<Asteroid> visible = getVisible();
        Asteroid lastVaporized = null;
        for (int vaporized = 0; vaporized < 200; vaporized++) { //vaporize 200 asteroids
            if (visible.isEmpty()) visible = getVisible(); //full round completed, renew visible ones
            lastVaporized = visible.remove();
        }
        assert lastVaporized != null; // an asteroid should always be visible
        return (long) lastVaporized.x() * 100 + lastVaporized.y();
    }


    public Queue<Asteroid> getVisible() {
        HashMap<Double, Asteroid> visible = new HashMap<>();
        //sort by distance (visible will always be before invisible)
        asteroids.stream().sorted(Comparator.comparingInt(center::distanceSquared))
                .skip(1) //skip the center itself
                .forEachOrdered(asteroid -> {
                    double angle = center.angle(asteroid);
                    if (!visible.containsKey(angle)) visible.put(angle, asteroid);
                });
        return visible.entrySet().stream()
                .sorted(Comparator.comparingDouble(Map.Entry::getKey))
                .map(Map.Entry::getValue)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public void parseAsteroids(PuzzleInput input) {
        int height = input.getLines().size(), width = input.getLines().get(0).length();
        asteroids = new ArrayList<>(height * width);
        int y = 0;
        for (String line : input) {
            int x = 0;
            for (char c : line.toCharArray()) {
                if (c == '#') asteroids.add(new Asteroid(x, y));
                x++;
            }
            y++;
        }
    }
}
