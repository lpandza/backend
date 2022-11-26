package com.example.backend.controller;

import com.example.backend.model.dto.PersonDto;
import com.example.backend.service.PersonService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

  private final PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping
  public ResponseEntity<?> getAllPersons() {
    return ResponseEntity.ok(personService.getAll());
  }

  @PostMapping
  public ResponseEntity<PersonDto> savePerson(@RequestBody @Valid PersonDto personDto) {
    return personService.save(personDto)
        .map(personDto1 -> ResponseEntity.status(HttpStatus.CREATED).body(personDto1))
        .orElseGet(() -> ResponseEntity.badRequest().build());
  }

  // TODO: 26.11.2022. get by oib


  // TODO: 26.11.2022. delete by oib

}
