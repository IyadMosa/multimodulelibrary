package com.imgware.multimodulelibrary.webClients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentWebClient extends AbstractWebClient {

    private final Logger logger = LoggerFactory.getLogger(PaymentWebClient.class);

    @Value("${payment.server.url}")
    private String serverUrl;

    public String getBaseUrl() {
        logger.debug("Payment service base url is: {}", serverUrl);
        return serverUrl;
    }

}

