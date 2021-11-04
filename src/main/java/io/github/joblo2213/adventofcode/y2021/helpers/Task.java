package io.github.joblo2213.adventofcode.y2021.helpers;

public abstract class Task {

    /**
     * Tries finding the puzzle input file for the current task and running it.
     * <p>
     * See {@link PuzzleInput#read(Class)}
     * @return the solution of the task
     * @throws Exception any uncaught exceptions occurring while running the task
     */
    @SuppressWarnings("UnusedReturnValue")
    public long run() throws Exception {
        long result = run(PuzzleInput.read(getClass()));
        System.out.printf("%4s%9s: %d%n","⭐", getClass().getSimpleName(), result);
        return result;
    }

    /**
     * Runs the task with the provided puzzle input.
     *
     * @param input input for which the task should be run
     * @return the solution of the task
     */
    public abstract long run(PuzzleInput input) throws Exception;
}
