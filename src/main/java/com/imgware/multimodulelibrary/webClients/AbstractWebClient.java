package com.imgware.multimodulelibrary.webClients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public abstract class AbstractWebClient {

    private final Logger logger = LoggerFactory.getLogger(AbstractWebClient.class);

    public abstract String getBaseUrl();

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private RestTemplate restTemplate;

    public <T> T get(String suffixUrl, Class<?> responseType) {
        logger.debug("Get for REST API: {}", getBaseUrl() + suffixUrl);
        return (T) webClientBuilder.build()
                .get()
                .uri(getBaseUrl() + suffixUrl)
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }

    public <T> T post(String suffixUrl, HttpEntity request, Class<T> responseType) throws RestClientException {
        logger.debug("Post for REST API: {}", getBaseUrl() + suffixUrl);
        return restTemplate.postForObject(getBaseUrl() + suffixUrl, request, responseType);
    }

    public void delete(String suffixUrl) {
        logger.debug("Delete for REST API: {}", getBaseUrl() + suffixUrl);
        restTemplate.delete(getBaseUrl() + suffixUrl);
//        webClientBuilder.build()
//                .method(HttpMethod.DELETE)
//                .uri(getBaseUrl() + suffixUrl)
//                .retrieve()
//                .bodyToMono(Void.class);
    }

    public <T> void put(String suffixUrl, HttpEntity request, Class<T> responseType) {
        logger.debug("Put for REST API: {}", getBaseUrl() + suffixUrl);
        restTemplate.put(getBaseUrl() + suffixUrl, request, responseType);
    }
}

