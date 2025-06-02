package dev.franciscohernandez.lamptracker.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import dev.franciscohernandez.lamptracker.service.models.LampModel;

public interface LampRepository extends MongoRepository<LampModel, String> {



    @Query("{ 'status' : ?0 }")
    List<LampModel> findByStatus(String status, Pageable pageable);

    Page<LampModel> findAll(Pageable pageable);
  
}
