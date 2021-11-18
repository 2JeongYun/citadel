package com.neukrang.citadel.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class ApiCaller {

    private final String apiKey;
    private final String baseUrl;

    public String call(String url, MethodType type, Map<String, String> headers, String reqMsg) {
        HttpURLConnection conn = null;
        headers.put("X-Riot-Token", apiKey);

        try {
            URL requestUrl = new URL(baseUrl + url);
            conn = (HttpURLConnection) requestUrl.openConnection();

            conn.setRequestMethod(type.toString());
            for (Map.Entry<String, String> header : headers.entrySet()) {
                conn.setRequestProperty(header.getKey(), header.getValue());
            }
            conn.setDoOutput(true);

            if (reqMsg != null) {
                try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()))) {
                    bw.write(reqMsg);
                    bw.flush();
                }
                //System.out.println(reqMsg);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line = null;
                StringBuffer sb = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                String responseMsg = sb.toString();
                log.debug(responseMsg);
                return responseMsg;
            } catch (FileNotFoundException e) {
                System.out.println("ERROR: " + conn.getResponseCode());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T call(String url, MethodType type, Map<String, String> headers, Class<T> returnType) {
        String json = call(url, type, headers);
        return JsonUtil.convertJsonToObject(json, returnType);
    }

    public <T> T call(String url, MethodType type, Map<String, String> headers, Object reqObj, Class<T> returnType) {
        String reqMsg = JsonUtil.convertObjectToJson(reqObj);
        String json = call(url, type, headers, reqMsg);
        return JsonUtil.convertJsonToObject(json, returnType);
    }

    public <T> T call(String url, MethodType type, Class<T> returnType) {
        String json = call(url, type, new HashMap<>());
        return JsonUtil.convertJsonToObject(json, returnType);
    }

    public String call(String url, MethodType type, Map<String, String> headers) {
        return call(url, type, headers, (String) null);
    }

    public String call(String url, MethodType type) {
        return call(url, type, new HashMap<>(), (String) null);
    }
}
