package com.game.checkersgame.model;

import java.util.*;

import com.game.checkersgame.service.Board;

public class HumanPlayer extends Player {
    private Scanner scanner = new Scanner(System.in);

    public HumanPlayer(char color) {
        super(color);
    }

    @Override
    public boolean makeMove(Board board) {
        System.out.println("Your move (" + color + "):");
        System.out.print("Enter move (fromRow fromCol toRow toCol)(e.g., 2 3 3 4): ");
        int fr = scanner.nextInt();
        int fc = scanner.nextInt();
        int tr = scanner.nextInt();
        int tc = scanner.nextInt();

        List<int[]> validMoves = board.getValidMoves(fr, fc);
        for (int[] move : validMoves) {
            if (move[0] == tr && move[1] == tc) {
                board.movePiece(fr, fc, tr, tc);
                return true;
            }
        }

        System.out.println("Invalid move. Try again.");
        return makeMove(board);
    }
}
