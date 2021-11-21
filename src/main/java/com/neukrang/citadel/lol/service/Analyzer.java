package com.neukrang.citadel.lol.service;

import com.neukrang.citadel.lol.domain.Rank;
import com.neukrang.citadel.lol.domain.Tier;
import com.neukrang.citadel.lol.domain.league.LeagueInfo;
import com.neukrang.citadel.lol.domain.summoner.Summoner;
import com.neukrang.citadel.lol.web.dto.SimpleProfileResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Analyzer {

    public SimpleProfileResponseDto makeSimpleProfile(Summoner summoner, List<LeagueInfo> leagueInfoList) {
        Tier highestTier = getHighestTier(leagueInfoList);

        return SimpleProfileResponseDto.builder()
                .name(summoner.getName())
                .tier(highestTier)
                .rank(getHighestRank(leagueInfoList, highestTier))
                .winRate(String.format("%.2f", computeLeagueWinRate(leagueInfoList)))
                .build();
    }

    public int getHighestRank(List<LeagueInfo> leagueInfoList, Tier highestTier) {
        return leagueInfoList.stream()
                .filter(leagueInfo -> leagueInfo.getTier() == highestTier)
                .map(leagueInfo -> leagueInfo.getRank())
                .min((o1, o2) -> o1 - o2)
                .orElse(Rank.UNRANK.getNumber());
    }

    public Tier getHighestTier(List<LeagueInfo> leagueInfoList) {
        return leagueInfoList.stream()
                .map(leagueInfo -> leagueInfo.getTier())
                .min((o1, o2) -> o1.getNumber() - o2.getNumber())
                .orElse(Tier.UNRANK);
    }

    public double computeLeagueWinRate(List<LeagueInfo> leagueInfoList) {
        int winCnt = 0, loseCnt = 0;
        for (LeagueInfo leagueInfo : leagueInfoList) {
            winCnt += leagueInfo.getWins();
            loseCnt += leagueInfo.getLosses();
        }

        if (winCnt + loseCnt == 0)
            return 0;
        return (double) winCnt / (winCnt + loseCnt);
    }
}