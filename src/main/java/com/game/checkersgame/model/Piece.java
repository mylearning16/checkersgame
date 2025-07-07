package com.game.checkersgame.model;

public class Piece {
    private char color; // 'r' or 'b'
    private boolean isKing;

    public Piece(char color) {
        this.color = color;
        this.isKing = false;
    }

    public char getColor() {
        return color;
    }

    public boolean isKing() {
        return isKing;
    }

    public void makeKing() {
        isKing = true;
    }

    @Override
    public String toString() {
        return isKing ? Character.toUpperCase(color) + "" : color + "";
    }
}

