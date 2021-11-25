package com.neukrang.citadel.lol.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConverterTest {

    @Test
    void getChampionNumber() {
        String name = "Gangplank";

        Long number = Converter.getChampionNumber(name);

        Assertions.assertThat(number).isEqualTo(41);
    }

    @Test
    void getChampionName() {
        Long number = 41L;

        String name = Converter.getChampionName(number);

        Assertions.assertThat(name).isEqualTo("Gangplank");
    }
}