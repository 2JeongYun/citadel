package com.neukrang.citadel.lol.web.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ApiResponseTest {

    ObjectMapper om = new ObjectMapper();

    @Test
    public void success() throws JsonProcessingException {
        TestObj testObj = new TestObj();

        String json = om.writeValueAsString(ApiResponse.success(testObj));

        Assertions.assertThat(json).contains("\"str\":\"Test\"");
        Assertions.assertThat(json).contains("\"num\":10");
        Assertions.assertThat(json).contains("\"error\":\"\"");
        Assertions.assertThat(json).contains("\"success\":true");
    }

    @Test
    public void fail() throws JsonProcessingException {
        String json = om.writeValueAsString(ApiResponse.fail("test fail"));

        Assertions.assertThat(json).doesNotContain("\"str\":\"Test\"");
        Assertions.assertThat(json).doesNotContain("\"num\":10");
        Assertions.assertThat(json).contains("\"error\":\"test fail\"");
        Assertions.assertThat(json).contains("\"success\":false");
    }

    static class TestObj {

        String str = "Test";
        int num = 10;

        public String getStr() {
            return str;
        }

        public int getNum() {
            return num;
        }
    }
}