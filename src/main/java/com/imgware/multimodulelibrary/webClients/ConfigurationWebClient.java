package com.imgware.multimodulelibrary.webClients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationWebClient extends AbstractWebClient {

    private final Logger logger = LoggerFactory.getLogger(ConfigurationWebClient.class);
    @Value("${configuration.server.url}")
    private String serverUrl;

    public String getBaseUrl() {
        logger.debug("Configuration service base url is: {}", serverUrl);
        return serverUrl;
    }

}

