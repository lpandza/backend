package com.example.backend.service;

import com.example.backend.model.Person;
import com.example.backend.model.dto.PersonDto;
import java.util.List;

public interface PersonService {

  List<PersonDto> getAll();

}
