package com.gondolier.gondola.BLL;

import com.gondolier.gondola.BLL.Interfaces.ITestService;
import com.gondolier.gondola.BLL.Models.TestDTO;
import com.gondolier.gondola.DAL.Models.Test;
import com.gondolier.gondola.DAL.Repositories.TestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class TestService implements ITestService {
    private final TestRepository tests;
    private final ModelMapper modelMapper;

    public TestService(TestRepository tests, ModelMapper modelMapper)
    {
        this.tests = tests;
        this.modelMapper = modelMapper;
    }

    public Flux<TestDTO> all()
    {
        return this.tests.findAll().map(test -> modelMapper.map(test, TestDTO.class));
    }

    public Mono<TestDTO> create(TestDTO testDTO)
    {
        var mappedTest = modelMapper.map(testDTO, Test.class);
        return this.tests.save(mappedTest).map(test -> modelMapper.map(test, TestDTO.class));
    }

    public Mono<TestDTO> get(String id)
    {
        return this.tests.findById(id).map(test -> modelMapper.map(test, TestDTO.class));
    }

    public Mono<TestDTO> update(String id, TestDTO testDTO)
    {
        return this.tests.findById(id).map(test -> {
            test.setName(testDTO.getName());
            return test;
        }).flatMap(this.tests::save).map(test -> modelMapper.map(test, TestDTO.class));
    }

    public Mono<Void> delete(String id)
    {
        return this.tests.deleteById(id);
    }
}
