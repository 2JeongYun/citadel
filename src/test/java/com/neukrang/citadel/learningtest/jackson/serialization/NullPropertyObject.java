package com.neukrang.citadel.learningtest.jackson.serialization;

public class NullPropertyObject {

    private String nullStr = null;
    private String notNullStr = "StringValue";
    private Long nullLong = null;
    private Long notNullLong = 1L;

    public String getNullStr() {
        return nullStr;
    }

    public String getNotNullStr() {
        return notNullStr;
    }

    public Long getNullLong() {
        return nullLong;
    }

    public Long getNotNullLong() {
        return notNullLong;
    }
}
