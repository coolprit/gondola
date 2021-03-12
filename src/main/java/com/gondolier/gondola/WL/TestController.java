package com.gondolier.gondola.WL;

import com.gondolier.gondola.BLL.Interfaces.ITestService;
import com.gondolier.gondola.BLL.Models.TestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value="/tests")
class TestController {
    private final ITestService testService;

    public TestController(ITestService testService)
    {
        this.testService = testService;
    }

    @GetMapping("")
    public Flux<TestDTO> all()
    {
        return this.testService.all();
    }

    @PostMapping("")
    public Mono<TestDTO> create(@RequestBody TestDTO test)
    {
        return this.testService.create(test);
    }

    @GetMapping("/{id}")
    public Mono<TestDTO> get(@PathVariable("id") String id)
    {
        return this.testService.get(id);
    }

    @PutMapping("/{id}")
    public Mono<TestDTO> update(@PathVariable("id") String id, @RequestBody TestDTO test)
    {
        return this.testService.update(id, test);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable("id") String id)
    {
        return this.testService.delete(id);
    }

}
