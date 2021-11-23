package com.neukrang.citadel.lol.domain.summoner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.neukrang.citadel.util.BaseTimeEntity;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@ToString
public class Summoner extends BaseTimeEntity {

    @Id
    private String puuid;

    private String id;
    private String name;
    private Long summonerLevel;

    public Summoner update(Summoner summoner) {
        this.id = summoner.getId();
        this.name = summoner.getName();
        this.summonerLevel = summoner.getSummonerLevel();

        return this;
    }
}
