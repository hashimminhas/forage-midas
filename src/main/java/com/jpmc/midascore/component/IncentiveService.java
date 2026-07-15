package com.jpmc.midascore.component;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;

@Component
public class IncentiveService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String INCENTIVE_URL = "http://localhost:8080/incentive";

    public float getIncentive(Transaction transaction) {
        Incentive incentive = restTemplate.postForObject(
            INCENTIVE_URL,
            transaction,
            Incentive.class
        );
        if (incentive == null) return 0f;
        return incentive.getAmount();
    }
}