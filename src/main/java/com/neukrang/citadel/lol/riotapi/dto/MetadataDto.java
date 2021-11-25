package com.neukrang.citadel.lol.riotapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetadataDto {

    private String matchId;
    private List<String> participants;
}
