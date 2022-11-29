package com.example.backend.service;

import com.example.backend.model.dto.PersonDto;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PersonService {

  List<PersonDto> getAll();

  Optional<PersonDto> save(PersonDto personDto);

  Optional<PersonDto> findByOib(String oib);

  Optional<PersonDto> writePersonToFile(String oib) throws IOException;

  void deletePerson(String oib) throws IOException;

}
