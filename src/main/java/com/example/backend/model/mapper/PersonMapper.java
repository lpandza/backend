package com.example.backend.model.mapper;

import com.example.backend.model.Person;
import com.example.backend.model.dto.PersonDto;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

  public Person toPerson(PersonDto personDto) {
    return new Person(null, personDto.getFirstName(), personDto.getLastName(), personDto.getOib(), personDto.getStatus());
  }

  public PersonDto toDto(Person person){
    return new PersonDto(person.getFirstName(), person.getLastName(), person.getOib(), person.getStatus());
  }

}
