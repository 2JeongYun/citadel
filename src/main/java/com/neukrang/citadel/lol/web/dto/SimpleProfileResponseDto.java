package com.neukrang.citadel.lol.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class SimpleProfileResponseDto {

    private String name;
    private String tier;
    private Integer rank;
    private Integer win;
    private Integer lose;
    private String winRate;
    @Builder.Default
    private List<String> mostThree =  new ArrayList<>();
}
