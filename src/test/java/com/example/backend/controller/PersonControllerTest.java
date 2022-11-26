package com.example.backend.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.example.backend.model.dto.PersonDto;
import com.example.backend.service.PersonService;
import com.example.backend.util.PersonUtil;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class PersonControllerTest {

  private PersonController personController;

  private PersonService personService;

  @BeforeEach
  public void setUp() {
    personService = mock(PersonService.class);
    personController = new PersonController(personService);
  }

  @Test
  @DisplayName(value = "Test saving person")
  public void testSavePerson() {
    PersonDto personDto = PersonUtil.newPersonDto();

    when(personService.save(any())).thenReturn(Optional.of(personDto));

    var result = personController.savePerson(any());

    Assertions.assertEquals(result.getStatusCode(), HttpStatus.CREATED);
    Assertions.assertEquals(result.getBody(), personDto);
    verify(personService, times(1)).save(any());
    verifyNoMoreInteractions(personService);
  }

  @Test
  @DisplayName(value = "Test getting all persons")
  public void testGettingAllPersons() {

    var result = personController.getAllPersons();

    Assertions.assertEquals(result.getStatusCode(), HttpStatus.OK);
    verify(personService, times(1)).getAll();
    verifyNoMoreInteractions(personService);
  }
}
