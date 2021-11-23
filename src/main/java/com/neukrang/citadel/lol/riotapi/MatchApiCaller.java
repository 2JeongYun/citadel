package com.neukrang.citadel.lol.riotapi;

import com.neukrang.citadel.lol.domain.summoner.Summoner;
import com.neukrang.citadel.util.ApiCaller;
import com.neukrang.citadel.util.MethodType;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class MatchApiCaller {

    private final ApiCaller asiaRiotApiCaller;
    private final String baseUrl = "/lol/match/v5/matches/";
    private final MatchIdRequest defaultRequest = MatchIdRequest.builder().build();

    public List<String> getMatchIdList(Summoner summoner) {
        return getMatchIdList(summoner, defaultRequest);
    }

    public List<String> getMatchIdList(Summoner summoner, MatchIdRequest request) {
        String url = baseUrl + "by-puuid/" + summoner.getPuuid() + "/ids";
        url = ApiCaller.getUrlWithQuery(url, request.getQueries());

        String[] idList =
                asiaRiotApiCaller.call(
                        url,
                        MethodType.GET,
                        String[].class
                );
        return Arrays.asList(idList);
    }

    @Builder
    @Getter
    static class MatchIdRequest {

        private Long startTime;
        private Long endTime;
        private Integer queue;
        private String type;
        @Builder.Default
        private Integer start = 0;
        @Builder.Default
        private Integer count = 20;

        public Map<String, String> getQueries() {
            Map<String, String> map = new HashMap<>();
            Field[] declaredFields = MatchIdRequest.class.getDeclaredFields();
            Arrays.stream(declaredFields)
                    .filter(field -> {
                        try {
                            return field.get(this) != null;
                        } catch (IllegalAccessException e) {
                            return false;
                        }
                    })
                    .forEach(field -> {
                        try {
                            map.put(field.getName(), field.get(this).toString());
                        } catch (IllegalAccessException e) {}
                    });
            return map;
        }
    }
}
