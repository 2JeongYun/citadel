package com.neukrang.citadel.config.security.apikey;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Slf4j
@Service
public class ApiKeyService {

    private final JavaMailSenderImpl javaMailSender;
    private final ApiKeyRepository apiKeyRepository;

    private ConcurrentHashMap<String, EmailVerificationCode> verificationCodeStore = new ConcurrentHashMap<>();
    private final int threshold = 100;

    @Value("${mail.sender}")
    private String emailSender;

    @Value("${server.host}")
    private String host;

    @Transactional
    public boolean sendApiKey(String email, String code) {
        EmailVerificationCode verificationCode = verificationCodeStore.get(code);
        if (verificationCode == null)
            return false;
        if (!verificationCode.getEmail().equals(email))
            return false;
        if (verificationCode.isExpired(LocalDateTime.now())) {
            verificationCodeStore.remove(verificationCode);
            return false;
        }

        verificationCodeStore.remove(verificationCode);

        String key = makeApiKey(email);
        String subject = "citadel API key";
        String content = "citadel API key 입니다.\n" +
                "key: " + key;
        String to = email;
        String errLog = email + "로 API key를 보내는 것에 실패 했습니다.";
        return sendMail(subject, content, to, errLog);
    }

    public boolean sendVerificationLink(String email) {
        String subject = "citadel API 인증 메일";
        String content = "citadel API 인증 메일입니다.\n" +
                "아래 링크를 클릭하면 API key가 이메일로 발송됩니다.\n" +
                "인증 링크: " + makeVerificationLink(email, verificationCodeStore);
        String to = email;
        String errLog = email + "로 인증 메일을 보내는 것에 실패 했습니다.";
        return sendMail(subject, content, to, errLog);
    }

    private String makeVerificationLink(String email, Map<String, EmailVerificationCode> store) {
        String code = UUID.randomUUID().toString()
                .replace("-", "");

        EmailVerificationCode verificationCode =
                EmailVerificationCode.builder()
                        .email(email)
                        .code(code)
                        .expiredDateTime(LocalDateTime.now().plusMinutes(2))
                        .build();

        store.put(verificationCode.getCode(), verificationCode);
        if (store.size() > threshold)
            removeExpiredCode(store);

        return host + "/api/v1/apikey/verification/?email=" + email + "&code=" + code;
    }

    private String makeApiKey(String email) {
        String uuid;
        do {
            uuid = UUID.randomUUID().toString().replace("-", "");
        } while (apiKeyRepository.findByKey(uuid) == null);

        ApiKey apiKey = new ApiKey(uuid, email);
        apiKeyRepository.save(apiKey);
        return apiKey.getKey();
    }

    @Transactional
    public boolean remove(String email, String key) {
        Optional<ApiKey> apiKey = apiKeyRepository.findByKey(key);
        if (!apiKey.isPresent())
            return false;
        if (!apiKey.get().getEmail().equals(email))
            return false;

        apiKeyRepository.remove(apiKey.get());
        return true;
    }

    public Optional<ApiKey> findApiKeyByKey(String apikey) {
        return apiKeyRepository.findByKey(apikey);
    }

    public boolean sendMail(String subject, String content, String to, String errorLogMessage) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail, "UTF-8");

            mailHelper.setFrom(emailSender);
            mailHelper.setTo(to);
            mailHelper.setSubject(subject);
            mailHelper.setText(content);

            javaMailSender.send(mail);
            return true;
        } catch (MessagingException e) {
            log.error(errorLogMessage);
            return false;
        }
    }

    public void removeExpiredCode(Map<String, EmailVerificationCode> store) {
        LocalDateTime now = LocalDateTime.now();
        store.entrySet().stream()
                .filter(entry -> entry.getValue().isExpired(now))
                .forEach(entry -> store.remove(entry.getKey()));
    }
}
