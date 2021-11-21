package com.neukrang.citadel.lol.riotapi;

import com.neukrang.citadel.util.ApiCaller;
import com.neukrang.citadel.util.MethodType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@SpringBootTest
public class RiotApiCallerTest {

    @Autowired
    ApiCaller riotApiCaller;

    @Test
    public void getSummoner() {
        String urlEncodedName = URLEncoder.encode("고급 참치캔", StandardCharsets.UTF_8);

        String result = riotApiCaller.call("/lol/summoner/v4/summoners/by-name/" + urlEncodedName,
                MethodType.GET);

        Assertions.assertThat(result).contains("\"name\":\"고급 참치캔\"");
    }
}
