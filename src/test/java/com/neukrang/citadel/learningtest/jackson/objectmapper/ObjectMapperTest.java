package com.neukrang.citadel.learningtest.jackson.objectmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ObjectMapperTest {

    String path = "./src/test/java/com/neukrang/citadel/learningtest/jackson/objectmapper";
    ObjectMapper om = new ObjectMapper();

    @Test
    public void 파일_읽기() throws IOException {
        String result = fileToString("testjson.txt");
        Assertions.assertThat(result).contains("{\n" +
                "\"simpleName\": \"name1\",\n" +
                "\"comName\": \"name2\"\n" +
                "}");
    }

    @Test
    public void 심플_객체_역직렬화() throws IOException {
        String fileStr = fileToString("testjson.txt");

        Simple simple = om.readValue(fileStr, Simple.class);
        Assertions.assertThat(simple.getSimpleName()).isEqualTo("name1");
    }

    public String fileToString(String fileName) throws IOException {
        File f = new File(path + "/" + fileName);
        BufferedReader br = new BufferedReader(new FileReader(f));
        StringBuffer sb = new StringBuffer();

        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }
}
