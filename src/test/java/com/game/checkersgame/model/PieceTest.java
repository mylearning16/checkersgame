package com.game.checkersgame.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {

    @Test
    public void testInitialState() {
        Piece p = new Piece('r');
        assertEquals('r', p.getColor());
        assertFalse(p.isKing());
    }

    @Test
    public void testMakeKing() {
        Piece p = new Piece('b');
        p.makeKing();
        assertTrue(p.isKing());
    }

    @Test
    public void testToStringForNormalAndKing() {
        Piece p = new Piece('b');
        assertEquals("b", p.toString());

        p.makeKing();
        assertEquals("B", p.toString());
    }
}
