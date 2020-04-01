package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    public Game() {
        Token t1 = new Token(1);
        Token t2 = new Token(2);
        Token t3 = new Token(3);
        Token t4 = new Token(4);
        Token t5 = new Token(5);

        List<Token> tokens = new ArrayList<>();
        tokens.add(t1);
        tokens.add(t2);
        tokens.add(t3);
        tokens.add(t4);
        tokens.add(t5);

        Board board = new Board(tokens);

        Player p1 = new Player("Alex", board);
        Player p2 = new Player("Alex2", board);

        List<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);

        System.out.println(board);

        for(Player player : players) {
            Thread thread = new Thread(player);
            thread.start();
        }
    }
}
