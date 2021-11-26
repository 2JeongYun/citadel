package com.neukrang.citadel.config.security.apikey;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class ApiKeyAuthManagerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void requestWithoutApiKey() throws Exception {
        String userName = "헝 꾸";
        String url = "/lol/v1/simple-profile/{name}";

        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get(url, userName)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Assertions.assertThat(content).contains("\"success\":false");
    }
}