package com.neukrang.citadel.lol.domain.summoner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@ToString
public class Summoner {

    @Id
    private String puuid;

    private String id;
    private String name;
    private Long summonerLevel;
}
