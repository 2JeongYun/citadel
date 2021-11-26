package com.neukrang.citadel.lol.service;

import com.neukrang.citadel.lol.domain.Converter;
import com.neukrang.citadel.lol.domain.Rank;
import com.neukrang.citadel.lol.domain.Tier;
import com.neukrang.citadel.lol.domain.league.LeagueInfo;
import com.neukrang.citadel.lol.domain.summoner.Summoner;
import com.neukrang.citadel.lol.riotapi.dto.ChampionMasteryDto;
import com.neukrang.citadel.lol.web.dto.SimpleProfileResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Analyzer {

    public SimpleProfileResponseDto makeSimpleProfile(Summoner summoner, List<LeagueInfo> leagueInfoList, List<ChampionMasteryDto> championMasteryDtoList) {
        Tier highestTier = getHighestTier(leagueInfoList);
        WinLoseTotal winLoseTotal = computeWinLoseTotal(leagueInfoList);

        return SimpleProfileResponseDto.builder()
                .name(summoner.getName())
                .tier(highestTier.toString())
                .rank(getHighestRank(leagueInfoList, highestTier))
                .winRate(String.format("%.2f", winLoseTotal.winRate))
                .win(winLoseTotal.winCnt)
                .lose(winLoseTotal.loseCnt)
                .mostThree(getMostThree(championMasteryDtoList))
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

    public List<String> getMostThree(List<ChampionMasteryDto> championMasteryDtoList) {
        return championMasteryDtoList.stream()
                .filter(championMasteryDto -> championMasteryDto.getChampionPoints() > 0)
                .sorted((o1, o2) -> o2.getChampionPoints() - o1.getChampionPoints())
                .limit(3)
                .map(championMasteryDto -> Converter.getChampionName(championMasteryDto.getChampionId()))
                .collect(Collectors.toList());
    }

    public WinLoseTotal computeWinLoseTotal(List<LeagueInfo> leagueInfoList) {
        WinLoseTotal winLoseTotal = new WinLoseTotal();
        int winCnt = 0, loseCnt = 0;
        for (LeagueInfo leagueInfo : leagueInfoList) {
            winCnt += leagueInfo.getWins();
            loseCnt += leagueInfo.getLosses();
        }

        winLoseTotal.winCnt = winCnt;
        winLoseTotal.loseCnt = loseCnt;

        if (winCnt + loseCnt == 0)
            winLoseTotal.winRate = 0;
        else
            winLoseTotal.winRate = (double) winCnt / (winCnt + loseCnt);
        return winLoseTotal;
    }

    static class WinLoseTotal {
        public int winCnt;
        public int loseCnt;
        public double winRate;
    }
}