package com.example.backend.service;

import com.example.backend.model.dto.PersonDto;
import java.util.List;
import java.util.Optional;

public interface PersonService {

  List<PersonDto> getAll();

  Optional<PersonDto> save(PersonDto personDto);

}
