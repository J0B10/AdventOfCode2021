package io.github.joblo2213.adventofcode.y2021.day16;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput.ofMulti;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day16A_Test {

    @Test
    public void testPacketBasics() {
        Packet p = Packet.parse("D2FE28");
        assertEquals("110100101111111000101", p.toString());
        assertEquals(6, p.getValue(0, 3));
        assertEquals(4, p.getValue(3, 3));
    }

    @Test
    public void testPacketParsing() {
        assertEquals(2021, Packet.parse("D2FE28").value);
        Packet p1 = Packet.parse("38006F45291200");
        assertEquals(10, p1.subPackets.get(0).value);
        assertEquals(20, p1.subPackets.get(1).value);
        Packet p2 = Packet.parse("EE00D40C823060");
        assertEquals(1, p2.subPackets.get(0).value);
        assertEquals(2, p2.subPackets.get(1).value);
        assertEquals(3, p2.subPackets.get(2).value);
    }

    @Test
    public void testAll() throws Exception {
        assertEquals(16, new Day16A().run(ofMulti("8A004A801A8002F478")));
        assertEquals(12, new Day16A().run(ofMulti("620080001611562C8802118E34")));
        assertEquals(23, new Day16A().run(ofMulti("C0015000016115A2E0802F182340")));
        assertEquals(20, new Day16A().run(ofMulti("9C0141080250320F1802104A08")));
        assertEquals(31, new Day16A().run(ofMulti("A0016C880162017C3686B18A3D4780")));
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true", disabledReason = "Disabled on GH Actions")
    public void task() throws Exception {
        new Day16A().run();
    }
}
