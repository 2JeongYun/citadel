package com.neukrang.citadel.learningtest.jackson.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class SerializationTest {

    ObjectMapper om = new ObjectMapper();

    @Test
    public void 직렬화_테스트() throws JsonProcessingException {
        SimpleValueObject simpleValueObject = new SimpleValueObject();
        simpleValueObject.setField1("필드1");
        simpleValueObject.setField2("필드2");

        String json = om.writeValueAsString(simpleValueObject);

        System.out.println(json);

        Assertions.assertThat(json).contains("\"fieldOne\":\"필드1\"");
        Assertions.assertThat(json).contains("\"field2\":\"필드2\"");
        Assertions.assertThat(json).doesNotContain("\"field1\":\"필드1\"");
    }

    @Test
    public void null_필드명_직렬화() throws JsonProcessingException {
        NullPropertyObject nullPropertyObject = new NullPropertyObject();

        String json = om.writeValueAsString(nullPropertyObject);

        System.out.println(json);

        Assertions.assertThat(json).contains("\"nullStr\":null");
        Assertions.assertThat(json).doesNotContain("\"notNullStr\":null");
        Assertions.assertThat(json).contains("\"nullLong\":null");
        Assertions.assertThat(json).doesNotContain("\"notNullLong\":null");
    }
}
