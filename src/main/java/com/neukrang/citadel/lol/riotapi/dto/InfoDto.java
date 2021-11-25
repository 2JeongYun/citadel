package com.neukrang.citadel.lol.riotapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoDto {

    private Long gameCreation;
    private Long gameDuration;
    private String gameMode;
    private Integer queueId;
    List<ParticipantDto> participants;
}
