package com.neukrang.citadel.lol.web.dto;

import com.neukrang.citadel.lol.domain.Tier;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SimpleProfileResponseDto {

    private String name;
    private Tier tier;
    private int rank;
    private String winRate;
}
