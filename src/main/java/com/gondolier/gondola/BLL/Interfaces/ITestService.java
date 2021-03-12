package com.gondolier.gondola.BLL.Interfaces;

import com.gondolier.gondola.BLL.Models.TestDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITestService {
    Flux<TestDTO> all();
    Mono<TestDTO> create(TestDTO testDTO);
    Mono<TestDTO> get(String id);
    Mono<TestDTO> update(String id, TestDTO testDTO);
    Mono<Void> delete(String id);
}
