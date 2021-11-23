package com.neukrang.citadel.learningtest.jackson.deserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neukrang.citadel.util.lol.TestUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeserializationTest {

    String path = "./src/test/java/com/neukrang/citadel/learningtest/jackson/deserialization/";
    ObjectMapper om = new ObjectMapper();

    @Test
    public void 역직렬화_필드명_매핑_JsonAlias() throws JsonProcessingException {
        String json = TestUtil.fileToString(path + "testjson.txt");

        TestObjectWithAlias testObjectWithAlias = om.readValue(json, TestObjectWithAlias.class);

        Assertions.assertThat(testObjectWithAlias.getField1()).isEqualTo("필드1");
        Assertions.assertThat(testObjectWithAlias.getField2()).isEqualTo("필드2");
    }

    @Test
    public void 역직렬화_필드명_매핑_JsonSetter() throws JsonProcessingException {
        String json = TestUtil.fileToString(path + "testjson.txt");

        TestObjectWithSetter testObjectWithJsonSetter = om.readValue(json, TestObjectWithSetter.class);

        Assertions.assertThat(testObjectWithJsonSetter.getField1()).isEqualTo("필드1");
        Assertions.assertThat(testObjectWithJsonSetter.getField2()).isEqualTo("필드2");
    }
}
