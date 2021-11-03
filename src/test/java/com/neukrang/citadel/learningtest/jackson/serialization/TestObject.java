package com.neukrang.citadel.learningtest.jackson.serialization;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class TestObject {

    String field1;
    String field2;

    // @JsonGetter("이름"): 직렬화시 이름의 프로퍼티 추가
    @JsonGetter("fieldOne")
    public String getField() {
        return field1;
    }

    @JsonIgnore
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
}
