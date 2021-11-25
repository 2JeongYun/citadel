package com.neukrang.citadel.lol.web.controller;

import com.neukrang.citadel.lol.service.AnalysisService;
import com.neukrang.citadel.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/lol/v1")
@RestController
public class AnalysisApi {

    private final AnalysisService analysisService;

    @PostMapping("/simple-profile")
    public ApiResponse simpleProfile(@RequestBody Map<String, String> params) {
        return ApiResponse.success(analysisService.makeSimpleProfile(params.get("name")));
    }
}