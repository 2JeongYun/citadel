package com.neukrang.citadel.util.lol;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.neukrang.citadel.lol.domain.league.LeagueInfo;
import com.neukrang.citadel.lol.domain.summoner.Summoner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class TestUtil {

    public static ObjectMapper om = new ObjectMapper().registerModule(new JavaTimeModule());
    public static final String BASE_PATH = "./src/test/java/com/neukrang/citadel/util/lol/";

    public static final String DEFAULT_SUMMONER = "SummonerHUNGGU.json";
    public static final String DEFAULT_LEAGUE_INFO = "LeagueInfoHUNGGU.json";

    public static String fileToString(String path) {
        File f = new File(path);
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            StringBuffer sb = new StringBuffer();

            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Summoner getTestSummoner() {
        return getTestSummoner(DEFAULT_SUMMONER);
    }

    public static Summoner getTestSummoner(String jsonFilePath) {
        try {
            return om.readValue(new File(BASE_PATH + jsonFilePath), Summoner.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("TestSummoner 역직렬화 실패");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(jsonFilePath + "를 찾을 수 없습니다.");
        }
    }

    public static List<LeagueInfo> getTestLeagueInfo(Summoner summoner) {
        return getTestLeagueInfo(DEFAULT_LEAGUE_INFO, summoner);
    }

    public static List<LeagueInfo> getTestLeagueInfo(String jsonFilePath, Summoner summoner) {
        try {
            List<LeagueInfo> leagueInfoList =
                    Arrays.asList(om.readValue(new File(BASE_PATH + jsonFilePath), LeagueInfo[].class));
            leagueInfoList.stream()
                    .forEach(leagueInfo -> leagueInfo.setSummoner(summoner));
            return leagueInfoList;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("TestLeagueInfo 역직렬화 실패");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(jsonFilePath + "를 찾을 수 없습니다.");
        }
    }

    public static <T> T setModifiedDate(T obj, LocalDateTime localDateTime) {
        try {
            Field field = obj.getClass().getSuperclass().getDeclaredField("modifiedDate");
            field.setAccessible(true);

            field.set(obj, localDateTime);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("수정 시각 바꾸기 실패");
        }
    }
}
