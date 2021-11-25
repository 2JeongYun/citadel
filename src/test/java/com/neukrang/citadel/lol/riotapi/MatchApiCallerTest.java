package com.neukrang.citadel.lol.riotapi;

import com.neukrang.citadel.config.Config;
import com.neukrang.citadel.lol.domain.summoner.Summoner;
import com.neukrang.citadel.lol.riotapi.dto.MatchDto;
import com.neukrang.citadel.util.ApiCaller;
import com.neukrang.citadel.util.lol.TestUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest(classes = {Config.class, MatchApiCaller.class})
class MatchApiCallerTest {

    @Autowired
    MatchApiCaller matchApiCaller;

    @Test
    @DisplayName("매치 ID 리스트 요청")
    void getMatchIdList() {
        Summoner summoner = TestUtil.getTestSummoner();

        List<String> matchIdList = matchApiCaller.getMatchIdList(summoner);

        matchIdList.stream().forEach(System.out::println);

        Assertions.assertThat(matchIdList).isNotNull();
        Assertions.assertThat(matchIdList).hasSizeGreaterThan(1);
    }

    @Test
    @DisplayName("올바른 요청 파라미터 값 생성")
    void requestGetQueries1() {
        MatchApiCaller.MatchIdRequest request = MatchApiCaller.MatchIdRequest.builder()
                .count(100)
                .startTime(1000L)
                .build();

        Map<String, String> queries = request.getQueries();
        String url = ApiCaller.getUrlWithQuery("/", queries);

        System.out.println(url);
        Assertions.assertThat(url).contains("/?");
        Assertions.assertThat(url.charAt(url.length() - 1)).isNotEqualTo('&');
        Assertions.assertThat(url).contains("count=100");
        Assertions.assertThat(url).contains("startTime=1000");
        Assertions.assertThat(url).contains("start=0");
    }

    @Test
    @DisplayName("디폴트 요청 파라미터 값 생성")
    void requestGetQueries2() {
        MatchApiCaller.MatchIdRequest request = MatchApiCaller.MatchIdRequest.builder().build();

        Map<String, String> queries = request.getQueries();
        String url = ApiCaller.getUrlWithQuery("/", queries);

        System.out.println(url);
        Assertions.assertThat(url).contains("/?");
        Assertions.assertThat(url.charAt(url.length() - 1)).isNotEqualTo('&');
        Assertions.assertThat(url).contains("count=20");
        Assertions.assertThat(url).contains("start=0");
    }

    @Test
    @DisplayName("매치 정보 가져오기")
    void getMatchDto() {
        MatchDto matchDto = matchApiCaller.getMatchDto("KR_5560532103");

        System.out.println(matchDto.toString());
    }
}