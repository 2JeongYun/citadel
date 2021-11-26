package com.neukrang.citadel.aspect;

import com.neukrang.citadel.lol.service.AnalysisService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class ControllerExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AnalysisService analysisService;

    @Test
    @WithMockUser
    void handleNotFoundError() throws Exception {
        String userName = "헝 꾸";
        String url = "/lol/v1/simple-profile/{name}";
        BDDMockito.given(analysisService.makeSimpleProfile(userName))
                .willThrow(new IllegalArgumentException());

        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get(url, userName)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Assertions.assertThat(content).contains("\"success\":false");
    }

    @Test
    @WithMockUser
    void handleError() throws Exception {
        String userName = "헝 꾸";
        String url = "/lol/v1/simple-profile/{name}";
        BDDMockito.given(analysisService.makeSimpleProfile(userName))
                .willThrow(new RuntimeException());

        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get(url, userName)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Assertions.assertThat(content).contains("\"success\":false");
    }
}