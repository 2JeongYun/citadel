package com.neukrang.citadel.lol.domain;

import java.util.Arrays;

public enum QueueType {

    FLEX("RANKED_FLEX_SR"), SOLO("RANKED_SOLO_5x5"), UNKNOWN("UNKNOWN");

    private final String riotQueueTypeValue;

    QueueType(String riotQueueTypeValue) {
        this.riotQueueTypeValue = riotQueueTypeValue;
    }

    public static QueueType convert(String riotQueueTypeValue) {
        return Arrays.stream(values())
                .filter(type -> type.riotQueueTypeValue.equals(riotQueueTypeValue))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
