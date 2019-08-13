package com.example.streamingservice;

import com.example.streamingservice.event.QuoteEvent;
import com.example.streamingservice.event.QuoteReloadEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication
public class StreamingApplication {

	private final AtomicReference<Set<String>> symbols = new AtomicReference<>(new HashSet<>());

	@Value("${quotes.url}")
	private String url;
	private final RestTemplate template = new RestTemplate();

	@Autowired
	private ApplicationEventPublisher eventBus;

	public static void main(String[] args) {
		SpringApplication.run(StreamingApplication.class, args);
	}

	@EventListener
	public void onApplicationLoad(ApplicationReadyEvent e) {
		Set<String> setOfSymbols = new HashSet<>(Arrays.asList("AAPL", "IBM", "EPAM", "TSLA", "ORCL", "GM"));
		symbols.set(setOfSymbols);
		log.info("loaded {}", setOfSymbols);
	}

	@Scheduled(fixedRate = 1000)
	public void reloadQuotes() {
		log.info("reloading quotes...");
		symbols.get().stream()
				.map(QuoteReloadEvent::new)
				.forEach(eventBus::publishEvent);
	}

	@EventListener
	public void onQuoteReloadEvent(QuoteReloadEvent e) {
		QuoteEvent quote = template.getForObject(URI.create(url + e.getSymbol()), QuoteEvent.class);
		// Show how to enable debug logging in runtime
		log.info("got quote {}", e.getSymbol());
		eventBus.publishEvent(quote);
	}
}