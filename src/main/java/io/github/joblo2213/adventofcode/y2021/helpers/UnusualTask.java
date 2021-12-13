package io.github.joblo2213.adventofcode.y2021.helpers;

/**
 * A daily task for AdventOfCode whose result is not a long value
 *
 * @param <T> type of the result
 */
public abstract class UnusualTask<T> extends Task {

    protected boolean multilineResults = true;
    protected boolean logResults = true;

    /**
     * @deprecated unusual Tasks do not return long values, use  {@link #runT(PuzzleInput)} instead
     */
    @Deprecated
    @Override
    public long run(PuzzleInput input) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("unusual Tasks do not return long values");
    }

    /**
     * @deprecated unusual Tasks do not return long values, use {@link #runT()} instead
     */
    @Deprecated
    @Override
    public long run() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("unusual Tasks do not return long values");
    }

    /**
     * Tries finding the puzzle input file for the current task and running it.
     * <p>
     * See {@link PuzzleInput#read(Class)}
     *
     * @return the solution of the task
     * @throws Exception any uncaught exceptions occurring while running the task
     */
    @SuppressWarnings("UnusedReturnValue")
    public T runT() throws Exception {
        T result = runT(PuzzleInput.read(getClass()));
        if (logResults && multilineResults) {
            logMultilineResult(getClass().getSimpleName(), result.toString());
        } else if (logResults) {
            logResult(getClass().getSimpleName(), result);
        }
        return result;
    }


    /**
     * Runs the task with the provided puzzle input.
     *
     * @param input input for which the task should be run
     * @return the solution of the task
     */
    public abstract T runT(PuzzleInput input) throws Exception;
}
