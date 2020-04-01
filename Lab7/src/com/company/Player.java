package com.company;

import java.util.ArrayList;
import java.util.List;

public class Player implements Runnable{
    private String name;
    private Board board;

    private List<Token> tokens;

    public Player(String name, Board board) {
        this.name = name;
        this.board = board;
        this.tokens = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        while(this.board.getTokens().size() > 0) {
        this.tokens.add(this.board.extractToken());
        }
        System.out.println("Player " + name + " has got " + this.tokens);
    }
}
