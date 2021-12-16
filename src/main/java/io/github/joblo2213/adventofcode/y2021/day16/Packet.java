package io.github.joblo2213.adventofcode.y2021.day16;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Packet {

    final String data;
    final int version;
    final int type;
    int length;
    long value = -1;
    int lengthType = -1;
    List<Packet> subPackets = List.of();

    Packet(String data) {
        this.data = data;
        length = data.length();
        version = getValue(0, 3);
        type = getValue(3, 3);
        loadValue();
        loadSubPackets();
    }

    private void loadValue() {
        if (isOperator()) return;
        long val = 0;
        int i = 6;
        boolean loop;
        do {
            assert i + 4 < length;
            loop = (getValue(i, 1) == 1);
            val = (val << 4) | getValue(i + 1, 4);
            assert val > 0; //hope for no integer overflows
            i += 5;
        } while (loop);
        value = val;
        length = i;
    }

    private void loadSubPackets() {
        if (!isOperator()) return;
        lengthType = getValue(6, 1);
        switch (lengthType) {
            case 0 -> {
                int l = getValue(7, 15);
                length = 22 + l;
                int i = 22;
                List<Packet> packets = new ArrayList<>();
                do {
                    Packet p = new Packet(data.substring(i));
                    i += p.length;
                    packets.add(p);
                } while (i + 6 < length);
                subPackets = Collections.unmodifiableList(packets);
            }
            case 1 -> {
                List<Packet> packets = new ArrayList<>();
                int l = getValue(7, 11);
                int i = 18;
                for (int j = 0; j < l; j++) {
                    Packet p = new Packet(data.substring(i));
                    i += p.length;
                    packets.add(p);
                }
                length = 18 + packets.stream().mapToInt(p -> p.length).sum();
                subPackets = Collections.unmodifiableList(packets);
            }
        }
    }

    static Packet parse(String hex) {
        String data = hex.chars()
                .map(Character::getNumericValue)
                .mapToObj(Integer::toBinaryString)
                .map(s -> IntStream.range(1, 4)
                        .dropWhile(i -> i < s.length())
                        .mapToObj(i -> "0")
                        .collect(Collectors.joining()) + s)
                .collect(Collectors.joining());
        return new Packet(data);
    }

    int getValue(int index, int length) {
        assert length < 32;
        return Integer.parseInt(data.substring(index, index + length), 2);
    }

    boolean isOperator() {
        return type != 4;
    }

    Stream<Packet> allPackets() {
        return Stream.concat(Stream.of(this), subPackets.stream().flatMap(Packet::allPackets));
    }

    public long getValue() {
        LongStream subPVals = subPackets.stream().mapToLong(Packet::getValue);
        assert type < 5 || subPackets.size() == 2;
        return switch (type) {
            case 4 -> value;
            case 0 -> subPVals.sum();
            case 1 -> subPVals.reduce(1, (a, b) -> a * b);
            case 2 -> subPVals.min().orElseThrow();
            case 3 -> subPVals.max().orElseThrow();
            case 5 -> subPackets.get(0).getValue() > subPackets.get(1).getValue() ? 1 : 0;
            case 6 -> subPackets.get(0).getValue() < subPackets.get(1).getValue() ? 1 : 0;
            case 7 -> subPackets.get(0).getValue() == subPackets.get(1).getValue() ? 1 : 0;
            default -> throw new RuntimeException("type =" + type);
        };
    }

    @Override
    public String toString() {
        return data.substring(0, length);
    }
}
