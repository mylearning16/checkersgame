package com.game.checkersgame.model;

import java.util.*;

import com.game.checkersgame.service.Board;

public class ComputerPlayer extends Player {
    private Random rand = new Random();

    public ComputerPlayer(char color) {
        super(color);
    }

    @Override
    public boolean makeMove(Board board) {
        List<int[]> allMoves = new ArrayList<>();

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = board.getPiece(r, c);
                if (p != null && p.getColor() == color) {
                    List<int[]> valid = board.getValidMoves(r, c);
                    for (int[] move : valid) {
                        allMoves.add(new int[]{r, c, move[0], move[1]});
                    }
                }
            }
        }

        if (allMoves.isEmpty()) return false;

        int[] chosen = allMoves.get(rand.nextInt(allMoves.size()));
        board.movePiece(chosen[0], chosen[1], chosen[2], chosen[3]);
        System.out.println("Computer moved from (" + chosen[0] + "," + chosen[1] + ") to (" + chosen[2] + "," + chosen[3] + ")");
        return true;
    }
}

