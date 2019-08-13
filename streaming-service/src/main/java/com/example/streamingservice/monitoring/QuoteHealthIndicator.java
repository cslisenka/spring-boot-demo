package com.example.streamingservice.monitoring;

import com.example.streamingservice.event.QuoteEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class QuoteHealthIndicator implements HealthIndicator {

    private RestTemplate template = new RestTemplate();

    @Value("${quotes.url}")
    private String url;

    @Override
    public Health health() {
        try {
            QuoteEvent quote = template.getForObject(URI.create(url + "AAPL"), QuoteEvent.class);
            return Health.up().withDetail("response", quote).build();
        } catch (Exception e) {
            return Health.down(e).build();
        }
    }
}