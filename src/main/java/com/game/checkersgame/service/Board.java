package com.game.checkersgame.service;

import java.util.*;

import com.game.checkersgame.model.Piece;

public class Board {
    private Piece[][] grid = new Piece[8][8];

    public Board() {
        setup();
    }

    public void setup() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if ((row + col) % 2 == 1) {
                    if (row < 3) grid[row][col] = new Piece('r');
                    else if (row > 4) grid[row][col] = new Piece('b');
                }
            }
        }
    }

    public void print() {
        System.out.println("  0 1 2 3 4 5 6 7");
        System.out.println("  - - - - - - - -");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + "|");
            for (int j = 0; j < 8; j++) {
                System.out.print((grid[i][j] != null ? grid[i][j] : "*") + " ");
            }
            System.out.println();
        }
    }

    public Piece getPiece(int row, int col) {
        return isInside(row, col) ? grid[row][col] : null;
    }

    public void movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        grid[toRow][toCol] = grid[fromRow][fromCol];
        grid[fromRow][fromCol] = null;

        //capture 
        if (Math.abs(toRow - fromRow) == 2) {
            int midRow = (fromRow + toRow) / 2;
            int midCol = (fromCol + toCol) / 2;
            grid[midRow][midCol] = null;
        }

        // king promotion when the black or red piece reaches end of the board
        Piece moved = grid[toRow][toCol];
        if ((moved.getColor() == 'r' && toRow == 7) || (moved.getColor() == 'b' && toRow == 0)) {
            moved.makeKing();
        }
    }

    public boolean isInside(int r, int c) {
        return r >= 0 && r < 8 && c >= 0 && c < 8;
    }

    public boolean hasMoves(char color) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = grid[r][c];
                if (p != null && p.getColor() == color) {
                    if (!getValidMoves(r, c).isEmpty()) return true;
                }
            }
        }
        return false;
    }

    public List<int[]> getValidMoves(int row, int col) {
        List<int[]> moves = new ArrayList<>();
        Piece p = grid[row][col];
        if (p == null) return moves;

        int dir = p.getColor() == 'r' ? 1 : -1;
        int[] dx = {-1, 1};

        for (int d : dx) {
            int r1 = row + dir;
            int c1 = col + d;
            if (isInside(r1, c1) && grid[r1][c1] == null) {
                moves.add(new int[]{r1, c1});
            }

            // Capture
            int rJump = row + dir * 2;
            int cJump = col + d * 2;
            int rMid = row + dir;
            int cMid = col + d;
            if (isInside(rJump, cJump) && grid[rJump][cJump] == null) {
                Piece mid = grid[rMid][cMid];
                if (mid != null && mid.getColor() != p.getColor()) {
                    moves.add(new int[]{rJump, cJump});
                }
            }
        }
        return moves;
    }
}

