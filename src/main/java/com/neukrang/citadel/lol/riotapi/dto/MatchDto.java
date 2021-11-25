package com.neukrang.citadel.lol.riotapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchDto {

    private MetadataDto metadata;
    private InfoDto info;
}
