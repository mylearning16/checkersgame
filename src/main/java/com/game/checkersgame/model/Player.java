package com.game.checkersgame.model;

import com.game.checkersgame.service.Board;

public abstract class Player {
    protected char color;

    public Player(char color) {
        this.color = color;
    }

    public char getColor() {
        return color;
    }

    public abstract boolean makeMove(Board board);
}
