package com.neukrang.citadel.lol.domain;

public enum Rank {

    I(1), II(2), III(3), IV(4);

    private final int number;

    Rank(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
