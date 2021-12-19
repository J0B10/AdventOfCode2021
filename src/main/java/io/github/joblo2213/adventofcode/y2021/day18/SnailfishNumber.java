package io.github.joblo2213.adventofcode.y2021.day18;


import java.util.Arrays;

import static java.lang.Integer.MIN_VALUE;

public class SnailfishNumber {

    private static final int[] EMPTY_HEAP = new int[63];

    private static final int PAIR = -1, EMPTY = MIN_VALUE;

    static {
        Arrays.fill(EMPTY_HEAP, EMPTY);
    }

    private int[] heap;

    private SnailfishNumber(int[] heap) {
        this.heap = heap;
        reduce();
    }

    public static SnailfishNumber parse(String s) {
        int[] heap = Arrays.copyOf(EMPTY_HEAP, EMPTY_HEAP.length);
        parse(s, heap, 0);
        return new SnailfishNumber(heap);
    }

    private static void parse(String s, int[] heap, int parentIndex) {
        assert s.startsWith("[") && s.endsWith("]");
        s = s.substring(1, s.length() - 1);
        int depth = 0;
        for (int i = 0; i < s.length(); i++) {
            assert depth >= 0;
            char ch = s.charAt(i);
            switch (ch) {
                case '[' -> depth++;
                case ']' -> depth--;
                case ',' -> {
                    if (depth == 0) {
                        String left = s.substring(0, i);
                        String right = s.substring(i + 1);
                        int c1 = leftChild(parentIndex);
                        int c2 = rightChild(parentIndex);
                        heap[parentIndex] = PAIR;
                        if (left.matches("\\d")) heap[c1] = Integer.parseInt(left);
                        else parse(left, heap, c1);
                        if (right.matches("\\d")) heap[c2] = Integer.parseInt(right);
                        else parse(right, heap, c2);
                        return;
                    }
                }
            }
        }
        throw new IllegalArgumentException(s);
    }

    private static int leftChild(int index) {
        return (2 * index) + 1;
    }

    private static int rightChild(int index) {
        return (2 * index) + 2;
    }

    private static int parent(int index) {
        return (index - 1) / 2;
    }

    /**
     * From <a href="https://stackoverflow.com/q/3305059">https://stackoverflow.com/q/3305059</a>
     *
     * @author Nulldevice
     */
    private static int log2(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        return 31 - Integer.numberOfLeadingZeros(n);
    }

    private boolean explode() {
        for (int i = 31; i < heap.length; i++) {
            //only cycle over heap at depth >4
            if (heap[i] != EMPTY) {
                int parent = parent(i);
                int v1 = heap[leftChild(parent)];
                int v2 = heap[rightChild(parent)];
                heap[leftChild(parent)] = EMPTY;
                heap[rightChild(parent)] = EMPTY;
                heap[parent] = 0;
                int leftN = leftChild(parent) - 1;
                int rightN = rightChild(parent) + 1;
                if (leftN > 30) {
                    //if has any left neighbours
                    while (heap[leftN] == EMPTY) leftN = parent(leftN);
                    heap[leftN] += v1;
                }
                if (rightN < heap.length) {
                    //if has any right neighbours
                    while (heap[rightN] == EMPTY) rightN = parent(rightN);
                    heap[rightN] += v2;
                }
                return true;
            }
        }
        return false;
    }

    private boolean split() {
        for (int i = 0; i < heap.length; i++) {
            int val = heap[i];
            if (val >= 10) {
                heap[i] = PAIR;
                heap[leftChild(i)] = val / 2; //rounded down
                heap[rightChild(i)] = val - (val / 2); //rounded up
                return true;
            }
        }
        return false;
    }

    public void reduce() {
        while (true) {
            if (explode()) continue;
            if (split()) continue;
            break;
        }
    }

    public void add(SnailfishNumber other) {
        int[] newHeap = Arrays.copyOf(EMPTY_HEAP, EMPTY_HEAP.length);
        newHeap[0] = PAIR;
        int iNew = 1, iThis = 0, iOther = 0;
        for (int i = 1; i <= 16; i = i << 1) {
            for (int j = 0; j < i; j++) {
                newHeap[iNew++] = heap[iThis++];
            }
            for (int j = 0; j < i; j++) {
                newHeap[iNew++] = other.heap[iOther++];
            }
        }
        heap = newHeap;
        reduce();
    }

    public long magnitude() {
        return magnitude(0);
    }

    private long magnitude(int index) {
        int val = heap[index];
        assert val != EMPTY;
        if (val == PAIR) return 3 * magnitude(leftChild(index)) + 2 * magnitude(rightChild(index));
        else return val;
    }


    @Override
    public String toString() {
        return toString(0);
    }

    private String toString(int index) {
        int val = heap[index];
        assert val != EMPTY;
        if (val == PAIR) return "[" + toString(leftChild(index)) + "," + toString(rightChild(index)) + "]";
        else return String.valueOf(heap[index]);
    }
}
