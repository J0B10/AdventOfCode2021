package io.github.joblo2213.adventofcode.y2020.day19;

/**
 * Returned if a {@link Rule} matched the beginning.
 * <p>
 * {@link #matched} contains the part that was matched and {@link #remaining} contains everything left.
 */
public record Match(String matched, String remaining) {

    /**
     * Check if the match was a full match (meaning the entire string was matched)
     * or if there is still unmatched content (false).
     *
     * @return {@code remaining.isEmpty()}
     */
    public boolean isFull() {
        return remaining.isEmpty();
    }

    @Override
    public String toString() {
        return "(" + matched + ")" + remaining;
    }
}
