package com.oreilly.restclient.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("WeakerAccess")
@SpringBootTest
@ExtendWith(SpringExtension.class)
class JokeServiceTest {
    private Logger logger = LoggerFactory.getLogger(JokeServiceTest.class);

    @Autowired
    private JokeService service;

    @Test
    void getJoke() {
        String joke = service.getJoke("Jair", "Aviles");
        logger.info(joke);
        assertTrue(joke.contains("Jair") || joke.contains("Aviles"));
    }

    @Test
    public void getJokeAsync() {
        String joke = service.getJokeAsync("Elber", "Gonzales")
                              .block(Duration.ofSeconds(2));
        logger.info(joke);
        assertTrue(joke.contains("Elber") || joke.contains("Gonzales"));
    }

    @Test
    public void useStepVerifier() {
        StepVerifier.create(service.getJokeAsync("Elber", "Gonzales"))
                    .assertNext(joke -> {
                        logger.info(joke);
                        assertTrue(joke.contains("Elber") || joke.contains("Gonzales"));
                    })
                    .verifyComplete();
    }
}