package com.imgware.multimodulelibrary.webClients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ReportWebClient extends AbstractWebClient {

    private final Logger logger = LoggerFactory.getLogger(ReportWebClient.class);

    @Value("${report.server.url}")
    private String serverUrl;

    public String getBaseUrl() {
        logger.debug("Report service base url is: {}", serverUrl);
        return serverUrl;
    }
}
