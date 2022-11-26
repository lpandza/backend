package com.example.backend.service.impl;

import com.example.backend.model.dto.PersonDto;
import com.example.backend.model.mapper.PersonMapper;
import com.example.backend.repository.PersonRepository;
import com.example.backend.service.PersonService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

  private final PersonRepository personRepository;

  private final PersonMapper personMapper;

  public PersonServiceImpl(PersonRepository personRepository, PersonMapper personMapper) {
    this.personRepository = personRepository;
    this.personMapper = personMapper;
  }

  @Override
  public List<PersonDto> getAll() {
    return personRepository.findAll().stream().map(personMapper::toDto).toList();
  }

  @Override
  public Optional<PersonDto> save(PersonDto personDto) {
    return Optional.of(personRepository.save(personMapper.toPerson(personDto))).map(personMapper::toDto);
  }
}
