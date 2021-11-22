package com.neukrang.citadel.lol.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neukrang.citadel.TestUtil;
import com.neukrang.citadel.lol.domain.summoner.Summoner;
import com.neukrang.citadel.lol.domain.summoner.SummonerRepository;
import com.neukrang.citadel.lol.riotapi.SummonerApiCaller;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class SummonerServiceUnitTest {

    ObjectMapper om = new ObjectMapper();
    SummonerRepository summonerRepository = mock(SummonerRepository.class);
    SummonerApiCaller summonerApiCaller = mock(SummonerApiCaller.class);
    SummonerService summonerService = new SummonerService(summonerRepository, summonerApiCaller);

    final String path = "./src/test/java/com/neukrang/citadel/lol/";

    @Test
    @DisplayName("리포지토리에 소환사 정보가 없을 때 api 호출 및 저장")
    public void findByName() throws JsonProcessingException {
        String json = TestUtil.fileToString(path + "SummonerTestJson.txt");
        Summoner summoner = om.readValue(json, Summoner.class);
        given(summonerRepository.findByName(summoner.getName())).willReturn(Optional.empty());
        given(summonerApiCaller.getSummonerByName(summoner.getName())).willReturn(Optional.of(summoner));

        summonerService.findByName(summoner.getName());

        verify(summonerRepository).findByName(summoner.getName());
        verify(summonerApiCaller).getSummonerByName(summoner.getName());
        verify(summonerRepository).save(summoner);
    }
}