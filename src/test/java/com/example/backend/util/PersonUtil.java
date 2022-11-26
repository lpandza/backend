package com.example.backend.util;

import com.example.backend.enumeration.Status;
import com.example.backend.model.Person;
import com.example.backend.model.dto.PersonDto;

public class PersonUtil {

  public static PersonDto newPersonDto() {
    return new PersonDto("Pero", "Peric", "89973031646", Status.AKTIVAN);
  }

  public static Person newPerson() {
    return new Person(1L, "Pero", "Peric", "89973031646", Status.AKTIVAN);
  }

}
