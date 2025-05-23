package com.cvds.parcial.back_parcial_3.repository;

import com.cvds.parcial.back_parcial_3.model.Specialty;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpecialtyRepository extends MongoRepository<Specialty, String> {

    boolean existsByName(String name);
}
