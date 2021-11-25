package com.neukrang.citadel.lol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.Map;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChampionInfo {

    private Map<String, Champion> data;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Champion {
        private String id;
        private Integer key;
    }
}
