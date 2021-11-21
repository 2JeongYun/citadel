package com.neukrang.citadel.config.security.apikey;

import com.neukrang.citadel.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/apikey")
public class ApiKeyController {

    private final ApiKeyService apiKeyService;
    private final String PARAM_EMAIL = "email";
    private final String PARAM_KEY = "key";

    @PostMapping
    public ApiResponse<String> makeApiKey(@RequestBody Map<String, String> params) {
        String email = params.get(PARAM_EMAIL);
        apiKeyService.sendVerificationLink(email);
        return ApiResponse.success("메일로 인증 링크가 발송 되었습니다");
    }

    @GetMapping("/verification")
    public ApiResponse<String> verifyCode(@RequestParam String email,
                                          @RequestParam String code) {
        if (apiKeyService.sendApiKey(email, code))
            return ApiResponse.success("메일로 API 키가 발송 되었습니다.");
        return ApiResponse.fail("메일 인증에 실패했습니다");
    }

    @DeleteMapping
    public ApiResponse<Boolean> removeApiKey(@RequestBody Map<String, String> params) {
        String email = params.get(PARAM_EMAIL);
        String key = params.get(PARAM_KEY);
        if (email == null || key == null)
            return ApiResponse.fail("email, key 가 올바르지 않습니다.");

        boolean success = apiKeyService.remove(email, key);
        return ApiResponse.success(success);
    }
}
