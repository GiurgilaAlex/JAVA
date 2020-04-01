package com.company;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Token> tokens;

    public Board(List<Token> tokens) {
        this.tokens = tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public Token extractToken() {
        Token tok = this.tokens.get(0);
        this.tokens.remove(tok);
        return tok;
    }

    @Override
    public String toString() {
        return "Board= " + tokens;
    }
}
