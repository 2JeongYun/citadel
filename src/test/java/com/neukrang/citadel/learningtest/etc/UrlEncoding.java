package com.neukrang.citadel.learningtest.etc;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlEncoding {

    @Test
    public void Url_인코딩() throws UnsupportedEncodingException {
        String target = "고급 참치캔";
        String result = URLEncoder.encode(target, "UTF-8");

        Assertions.assertThat(result).isEqualTo("%EA%B3%A0%EA%B8%89%20%EC%B0%B8%EC%B9%98%EC%BA%94");
    }
}
