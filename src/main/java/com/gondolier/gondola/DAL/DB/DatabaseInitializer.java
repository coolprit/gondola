package com.gondolier.gondola.DAL.DB;

import com.gondolier.gondola.DAL.Models.Test;
import com.gondolier.gondola.DAL.Repositories.TestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Slf4j
class DatabaseInitializer {
    private final TestRepository tests;

    public DatabaseInitializer(TestRepository tests) {
        this.tests = tests;
    }

    @EventListener(value = ContextRefreshedEvent.class)
    public void initialize() {
        initializeTests();
    }

    private void initializeTests() {
        log.info("Initalizing tests...");
        this.tests.deleteAll().thenMany(Flux.just("test", "test2").flatMap(name -> {
            Test test = Test.builder().name(name).build();
            return this.tests.save(test);
        })).subscribe(null, null, () -> log.info("Initialized tests"));
    }
}
