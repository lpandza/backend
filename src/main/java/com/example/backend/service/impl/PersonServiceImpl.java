package com.example.backend.service.impl;

import com.example.backend.enumeration.Status;
import com.example.backend.model.dto.PersonDto;
import com.example.backend.model.mapper.PersonMapper;
import com.example.backend.repository.PersonRepository;
import com.example.backend.service.FileService;
import com.example.backend.service.PersonService;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServiceImpl implements PersonService {

  private final PersonRepository personRepository;

  private final PersonMapper personMapper;

  private final FileService fileService;

  public PersonServiceImpl(PersonRepository personRepository, PersonMapper personMapper, FileService fileService) {
    this.personRepository = personRepository;
    this.personMapper = personMapper;
    this.fileService = fileService;
  }

  @Override
  public List<PersonDto> getAll() {
    return personRepository.findAll().stream().map(personMapper::toDto).toList();
  }

  @Override
  public Optional<PersonDto> save(PersonDto personDto) {
    personDto.setStatus(Status.NEAKTIVAN);
    return Optional.of(personRepository.save(personMapper.toPerson(personDto))).map(personMapper::toDto);
  }

  @Override
  public Optional<PersonDto> findByOib(String oib) {
    return Optional.ofNullable(personRepository.findFirstByOib(oib).map(personMapper::toDto)
        .orElseThrow(() -> new EntityNotFoundException("Osoba sa oibom " + oib + " ne postoji")));
  }

  @Override
  @Transactional
  public Optional<PersonDto> writePersonToFile(String oib) throws IOException {
    PersonDto personDto = findByOib(oib).get();

    if (personDto.getStatus().toString().equals("NEAKTIVAN")) {
      personDto.setStatus(Status.AKTIVAN);
      personRepository.save(personMapper.toPerson(personDto));
      fileService.saveFile(personDto.toString(), oib);
    }

    return Optional.of(personDto);
  }

  @Override
  @Transactional
  public void deletePerson(String oib) throws IOException {
    PersonDto personDto = findByOib(oib).get();

    personDto.setStatus(Status.NEAKTIVAN);
    fileService.saveFile(personDto.toString(), oib);

    personRepository.deleteByOib(oib);
  }
}
