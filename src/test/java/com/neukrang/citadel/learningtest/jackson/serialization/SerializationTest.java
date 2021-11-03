package com.neukrang.citadel.learningtest.jackson.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class SerializationTest {

    ObjectMapper om = new ObjectMapper();

    @Test
    public void 직렬화_필드명_변경() throws JsonProcessingException {
        TestObject testObject = new TestObject();
        testObject.setField1("필드1");
        testObject.setField2("필드2");

        String resultJson = om.writeValueAsString(testObject);

        System.out.println(resultJson);

        Assertions.assertThat(resultJson).contains("\"fieldOne\":\"필드1\"");
        Assertions.assertThat(resultJson).contains("\"field2\":\"필드2\"");
        Assertions.assertThat(resultJson).doesNotContain("\"field1\":\"필드1\"");
    }
}
