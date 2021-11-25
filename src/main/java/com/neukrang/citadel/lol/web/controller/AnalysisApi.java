package com.neukrang.citadel.lol.web.controller;

import com.neukrang.citadel.lol.service.AnalysisService;
import com.neukrang.citadel.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/lol")
@RestController
public class AnalysisApi {

    private final AnalysisService analysisService;

    @GetMapping("/simple-profile/{name}")
    public ApiResponse simpleProfile(@PathVariable String name) {
        return ApiResponse.success(analysisService.makeSimpleProfile(name));
    }
}