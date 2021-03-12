package com.gondolier.gondola.DAL.Repositories;

import com.gondolier.gondola.DAL.Models.Test;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends ReactiveCrudRepository<Test, String> { }
