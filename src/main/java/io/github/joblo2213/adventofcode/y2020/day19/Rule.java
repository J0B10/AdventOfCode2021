package io.github.joblo2213.adventofcode.y2020.day19;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Defines a rule that can be matched against a string
 */
public interface Rule {

    /**
     * Try if the start of the given string (or the entire string) matches the rule.
     *
     * @param string input string that should be checked
     * @return list of any found matches,
     * may contain full matches or matches that only matched a part of the string.
     * Use {@link Match#isFull()} to distinguish between them.
     */
    List<Match> tryMatch(String string);

    /**
     * Try if a previous matches remaining string starts matching the rule.
     *
     * @param match a previous match that isn't full already (otherwise no matches will be found)
     * @return list of any found matches,
     * may contain full matches or matches that only matched a part of the string.
     * Use {@link Match#isFull()} to distinguish between them.
     */
    default List<Match> tryMatch(Match match) {
        if (match.isFull()) return List.of();
        return tryMatch(match.remaining())
                .stream()
                .map(newMatch -> new Match(match.matched() + newMatch.matched(), newMatch.remaining()))
                .collect(Collectors.toList());
    }
}
