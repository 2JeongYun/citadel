package com.neukrang.citadel.lol.domain;

import java.util.Arrays;
import java.util.Optional;

public enum Tier {

    UNRANK(10),
    IRON(9),
    BRONZE(8),
    SILVER(7),
    GOLD(6),
    PLATINUM(5),
    DIAMOND(4),
    MASTER(3),
    GRANDMASTER(2),
    CHALLENGER(1);

    private final int number;

    Tier(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public static Optional<Tier> findByNumber(int number) {
        return Arrays.stream(values())
                .filter(tier -> tier.getNumber() == number)
                .findFirst();
    }
}
