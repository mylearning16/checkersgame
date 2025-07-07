package com.game.checkersgame.app;

import com.game.checkersgame.model.ComputerPlayer;
import com.game.checkersgame.model.HumanPlayer;
import com.game.checkersgame.model.Player;
import com.game.checkersgame.service.Board;

public class Game {
    public static void main(String[] args) {
        Board board = new Board();
        Player red = new HumanPlayer('r');
        Player black = new ComputerPlayer('b');
        Player current = red;

        while (true) {
            board.print();
            if (!board.hasMoves(current.getColor())) {
                System.out.println("Player " + current.getColor() + " has no moves. Game over.");
                break;
            }

            current.makeMove(board);
            // Switch to other player
            current = (current == red) ? black : red;
        }
    }
}

