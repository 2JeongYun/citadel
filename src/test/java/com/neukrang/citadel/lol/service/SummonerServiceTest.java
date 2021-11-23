package com.neukrang.citadel.lol.service;

import com.neukrang.citadel.lol.domain.summoner.Summoner;
import com.neukrang.citadel.lol.domain.summoner.SummonerRepository;
import com.neukrang.citadel.lol.riotapi.SummonerApiCaller;
import com.neukrang.citadel.util.lol.TestUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class SummonerServiceTest {

    @SpyBean
    SummonerRepository summonerRepository;

    @MockBean
    SummonerApiCaller summonerApiCaller;

    @Autowired
    SummonerService summonerService;

    @Test
    @Transactional
    @DisplayName("DB에 없는 유저를 찾을 때 Riot API 호출 및 저장, 반환")
    void findByName1() {
        Summoner summoner = TestUtil.getTestSummoner();
        given(summonerApiCaller.getSummonerByName(summoner.getName()))
                .willReturn(Optional.of(summoner));

        Summoner foundSummoner = summonerService.findByName(summoner.getName());

        verify(summonerApiCaller).getSummonerByName(summoner.getName());
        Assertions.assertThat(summoner)
                .usingRecursiveComparison()
                .isEqualTo(foundSummoner);
    }

    @Test
    @Transactional
    @DisplayName("DB에 있는 업데이트가 필요없는 유저를 찾았을 때, Riot API를 호출하지 않음")
    void findByName2() {
        Summoner summoner = TestUtil.getTestSummoner();
        summonerRepository.save(summoner);

        Summoner foundSummoner = summonerService.findByName(summoner.getName());

        verify(summonerApiCaller, times(0))
                .getSummonerByName(summoner.getName());
        Assertions.assertThat(summoner)
                .usingRecursiveComparison()
                .isEqualTo(foundSummoner);
    }
}