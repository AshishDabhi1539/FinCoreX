package com.tss.model;

public abstract class Player {
    protected String name;
    protected char symbol;
    protected IMoveStrategy moveStrategy;

    public Player(String name, char symbol, IMoveStrategy moveStrategy) {
        this.name = name;
        this.symbol = symbol;
        this.moveStrategy = moveStrategy;
    }

    public abstract void makeMove(Board board);

    public char getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }
}
