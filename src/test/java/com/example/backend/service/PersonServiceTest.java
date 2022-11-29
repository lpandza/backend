package com.example.backend.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.example.backend.model.Person;
import com.example.backend.model.dto.PersonDto;
import com.example.backend.model.mapper.PersonMapper;
import com.example.backend.repository.PersonRepository;
import com.example.backend.service.impl.PersonServiceImpl;
import com.example.backend.util.PersonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersonServiceTest {

  private PersonRepository personRepository;

  private PersonMapper personMapper;

  private PersonService personService;

  private FileService fileService;

  @BeforeEach
  public void setUp() {
    personRepository = mock(PersonRepository.class);
    personMapper = mock(PersonMapper.class);
    fileService = mock(FileService.class);
    personService = new PersonServiceImpl(personRepository, personMapper, fileService);
  }

  @Test
  @DisplayName(value = "Test saving new person")
  void savePerson() {
    PersonDto personDto = PersonUtil.newPersonDto();
    Person person = PersonUtil.newPerson();

    when(personMapper.toPerson(personDto)).thenReturn(person);
    when(personRepository.save(person)).thenReturn(person);

    personService.save(personDto);

    verify(personMapper, times(1)).toPerson(personDto);
    verify(personMapper, times(1)).toDto(person);
    verify(personRepository, times(1)).save(person);

    verifyNoMoreInteractions(personRepository, personMapper);
  }

}
