package com.neukrang.citadel.learningtest.jackson.deserialization;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TestObjectWithSetter {

    String field1;
    String field2;

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    @JsonSetter("exField")
    public void setEx(String exField) {
        this.field1 = exField;
    }
}
