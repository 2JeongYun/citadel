package com.neukrang.citadel.lol.domain.league;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.neukrang.citadel.lol.domain.QueueType;
import com.neukrang.citadel.lol.domain.Rank;
import com.neukrang.citadel.lol.domain.Tier;
import com.neukrang.citadel.lol.domain.summoner.Summoner;
import com.neukrang.citadel.util.BaseTimeEntity;
import lombok.Getter;

import javax.persistence.*;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class LeagueInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "league_info_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private QueueType queueType;

    @Enumerated(EnumType.STRING)
    private Tier tier;

    private int rank;
    private int leaguePoints;
    private int wins;
    private int losses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "puuid")
    private Summoner summoner;

    @JsonSetter("queueType")
    public void setQueueTypeWithJson(String queueTypeStr) {
        this.queueType = QueueType.convert(queueTypeStr);
    }

    @JsonSetter("rank")
    public void setTierWithJson(String rankStr) {
        this.rank = Rank.valueOf(rankStr).getNumber();
    }

    public void setSummoner(Summoner summoner) {
        this.summoner = summoner;
    }
}
