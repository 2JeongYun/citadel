package com.neukrang.citadel.lol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeagueInfo {

    private QueueType queueType;
    private Tier tier;
    private int rank;
    private int leaguePoints;
    private int wins;
    private int losses;

    @JsonSetter("queueType")
    public void setQueueTypeWithJson(String queueTypeStr) {
        this.queueType = QueueType.convert(queueTypeStr);
    }

    @JsonSetter("rank")
    public void setTierWithJson(String rankStr) {
        this.rank = Rank.valueOf(rankStr).getNumber();
    }
}
