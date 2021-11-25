package com.neukrang.citadel.lol.riotapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParticipantDto {

    private Integer assists;
    private String championName;
    private Integer deaths;
    private Boolean firstBloodKill;
    private Boolean firstTowerKill;
    private Boolean firstTowerAssist;
    private Boolean gameEndedInSurrender;
    private Integer goldEarned;
    private Integer kills;
    private String lane;
    private Integer largestKillingSpree;
    private Integer largestMultiKill;
    private Integer neutralMinionsKilled;
    private Integer pentaKills;
    private Integer quadraKills;
    private String role;
    private String teamPosition;
    private Integer damageDealtToBuildings;
    private Integer totalDamageDealtToChampions;
    private Integer totalDamageTaken;
    private Integer totalHeal;
    private Integer totalHealsOnTeammates;
    private Integer totalMinionsKilled;
    private Integer wardsKilled;
    private Integer wardsPlaced;
    private Boolean win;
}
