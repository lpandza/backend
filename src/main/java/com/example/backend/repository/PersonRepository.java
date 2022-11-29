package com.example.backend.repository;

import com.example.backend.model.Person;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

  Optional<Person> findFirstByOib(String oib);

  void deleteByOib(String oib);

}
