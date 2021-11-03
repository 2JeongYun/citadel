package com.neukrang.citadel.learningtest.jackson.objectmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.neukrang.citadel.learningtest.jackson.testutil.TestUtil.fileToString;

public class ObjectMapperTest {

    String path = "./src/test/java/com/neukrang/citadel/learningtest/jackson/objectmapper/";
    ObjectMapper om = new ObjectMapper();

    @Test
    public void 파일_읽기() {
        String result = fileToString(path + "testjson.txt");
        Assertions.assertThat(result).contains("{\n" +
                "\"simpleName\": \"name1\",\n" +
                "\"comName\": \"name2\"\n" +
                "}");
    }

    @Test
    public void 심플_객체_역직렬화() throws IOException {
        String fileStr = fileToString(path + "testjson.txt");

        Simple simple = om.readValue(fileStr, Simple.class);
        Assertions.assertThat(simple.getSimpleName()).isEqualTo("name1");
    }
}
