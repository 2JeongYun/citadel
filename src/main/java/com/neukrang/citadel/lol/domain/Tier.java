package com.neukrang.citadel.lol.domain;

public enum Tier {

    IRON(9),
    BRONZE(8),
    SILVER(7),
    GOLD(6),
    PLATINUM(5),
    DIAMOND(4),
    MASTER(3),
    GRANDMASTER(2),
    CHALLENGER(1);

    private final int order;

    Tier(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }
}
