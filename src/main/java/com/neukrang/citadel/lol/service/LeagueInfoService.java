package com.neukrang.citadel.lol.service;

import com.neukrang.citadel.lol.domain.league.LeagueInfo;
import com.neukrang.citadel.lol.domain.league.LeagueInfoRepository;
import com.neukrang.citadel.lol.domain.summoner.Summoner;
import com.neukrang.citadel.lol.riotapi.LeagueApiCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LeagueInfoService {

    private final LeagueInfoRepository leagueInfoRepository;
    private final LeagueApiCaller leagueApiCaller;

    @Value("${citadel.update-period.day}")
    private int updatePeriod;

    @Transactional
    public List<LeagueInfo> findBySummoner(Summoner summoner) {
        List<LeagueInfo> leagueInfoList = leagueInfoRepository.findBySummoner(summoner);
        if (leagueInfoList.size() > 0) {
            if (needToUpdate(leagueInfoList))
                leagueInfoList = updateLeagueInfoByRiot(leagueInfoList, summoner);

            return leagueInfoList;
        }

        leagueInfoList = saveLeagueInfoByRiot(summoner);
        return leagueInfoList;
    }

    @Transactional
    public List<LeagueInfo> saveLeagueInfoByRiot(Summoner summoner) {
        List<LeagueInfo> leagueInfoList = leagueApiCaller.getLeagueInfoList(summoner);
        leagueInfoList.stream()
                .forEach(leagueInfo -> leagueInfoRepository.save(leagueInfo));
        return leagueInfoList;
    }

    @Transactional
    public List<LeagueInfo> updateLeagueInfoByRiot(List<LeagueInfo> leagueInfoList, Summoner summoner) {
        List<LeagueInfo> freshLeagueInfoList = leagueApiCaller.getLeagueInfoList(summoner);
        leagueInfoList.stream()
                .forEach(leagueInfo -> {
                    Optional<LeagueInfo> updateInfo = freshLeagueInfoList.stream()
                            .filter(freshInfo -> freshInfo.getQueueType() == leagueInfo.getQueueType())
                            .findFirst();
                    if (updateInfo.isPresent())
                        leagueInfo.update(updateInfo.get());
                    else
                        leagueInfoRepository.remove(leagueInfo);
                });
        return leagueInfoList;
    }

    private boolean needToUpdate(List<LeagueInfo> leagueInfoList) {
        return leagueInfoList.stream()
                .anyMatch(leagueInfo -> leagueInfo.needToUpdate(updatePeriod));
    }
}
