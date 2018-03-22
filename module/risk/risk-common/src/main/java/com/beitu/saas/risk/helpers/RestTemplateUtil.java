package com.beitu.saas.risk.helpers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 柳朋朋
 * @Create 2017-05-03 12:05
 */
public class RestTemplateUtil implements Serializable {
    private RestTemplate restTemplate = new RestTemplate();

    /**
     * get 请求
     *
     * @param url
     * @param params
     * @return
     */
    public String getRequest(String url, MultiValueMap<String, String> params) {
        String requestUrl = UriComponentsBuilder
                .fromHttpUrl(url)
                .queryParams(params).build().toUriString();
        ResponseEntity responseEntity = restTemplate.getForEntity(requestUrl, String.class, new HashMap());
        return responseEntity.getBody().toString();
    }

    /**
     * post 请求
     */
    public String postRequest(String url, Map<String, Object> body) {
        try {
            body.put("httpMethod", "POST");
            String requestUrl = UriComponentsBuilder
                    .fromHttpUrl(url)
                    .build().toUriString();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json;charset=utf-8");
            HttpEntity request = null;
            request = new HttpEntity(JSONUtils.obj2json(body), headers);
            ResponseEntity responseEntity = restTemplate.postForEntity(requestUrl, request, String.class);
            return responseEntity.getBody().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
