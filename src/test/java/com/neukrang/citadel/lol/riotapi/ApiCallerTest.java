package com.neukrang.citadel.lol.riotapi;

import com.neukrang.citadel.util.MethodType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@SpringBootTest
public class ApiCallerTest {

    @Autowired
    ApiCaller apiCaller;

    @Test
    public void getSummoner() throws UnsupportedEncodingException {
        String urlEncodedName = URLEncoder.encode("고급 참치캔", "UTF-8");

        String result = apiCaller.call("/lol/summoner/v4/summoners/by-name/" + urlEncodedName,
                MethodType.GET);

        Assertions.assertThat(result).contains("\"name\":\"고급 참치캔\"");
    }
}
