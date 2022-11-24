package com.example.backend.model.dto;

import com.example.backend.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PersonDto {

  private String firstName;

  private String lastName;

  private String oib;

  private Status status;

}
