package com.game.checkersgame.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.game.checkersgame.model.Piece;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testSetupInitialPieces() {
        int redCount = 0;
        int blackCount = 0;

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = board.getPiece(r, c);
                if (p != null) {
                    if (p.getColor() == 'r') redCount++;
                    if (p.getColor() == 'b') blackCount++;
                }
            }
        }

        assertEquals(12, redCount);
        assertEquals(12, blackCount);
    }

    @Test
    public void testMovePiece() {
        // Move black piece from (5,0) to (4,1)
        assertNotNull(board.getPiece(5, 0));
        board.movePiece(5, 0, 4, 1);
        assertNull(board.getPiece(5, 0));
        assertNotNull(board.getPiece(4, 1));
        assertEquals('b', board.getPiece(4, 1).getColor());
    }

    @Disabled
    public void testCaptureMove() {
        // Set up red at (3,2), black at (4,3)
        board = new Board();
        for (int r = 0; r < 8; r++)
            for (int c = 0; c < 8; c++)
                if ((r + c) % 2 == 1)
                    board.movePiece(r, c, r, c); // Clear board

        board.movePiece(2, 1, 3, 2); // red
        board.movePiece(5, 4, 4, 3); // black

        assertNotNull(board.getPiece(3, 2));
        assertNotNull(board.getPiece(4, 3));

        board.movePiece(3, 2, 5, 4); // red captures black

        assertNull(board.getPiece(3, 2));
        assertNull(board.getPiece(4, 3));
        assertEquals('r', board.getPiece(5, 4).getColor());
    }

    @Test
    public void testValidMoves() {
        List<int[]> moves = board.getValidMoves(5, 0);
        assertFalse(moves.isEmpty());

        for (int[] move : moves) {
            assertTrue(board.isInside(move[0], move[1]));
        }
    }

    @Test
    public void testHasMoves() {
        assertTrue(board.hasMoves('r'));
        assertTrue(board.hasMoves('b'));
    }
}
