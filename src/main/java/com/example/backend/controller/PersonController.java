package com.example.backend.controller;

import com.example.backend.model.dto.PersonDto;
import com.example.backend.service.PersonService;
import java.io.IOException;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @GetMapping("/with-oib")
  public ResponseEntity<PersonDto> getByOib(@RequestParam String oib) {
    return personService.findByOib(oib)
        .map(personDto -> ResponseEntity.status(HttpStatus.OK).body(personDto))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping("/{oib}/file")
  public ResponseEntity<PersonDto> generateFile(@PathVariable String oib) throws IOException {
    return personService.writePersonToFile(oib)
        .map(personDto -> ResponseEntity.status(HttpStatus.OK).body(personDto))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }


  @DeleteMapping
  public ResponseEntity<HttpStatus> deletePerson(@RequestParam String oib) throws IOException {
    personService.deletePerson(oib);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
