package com.example.backend.model.dto;

import com.example.backend.enumeration.Status;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class PersonDto {

  @NotBlank(message = "Ovo polje je obavezno")
  private String firstName;

  @NotBlank(message = "Ovo polje je obavezno")
  private String lastName;

  @NotBlank(message = "Ovo polje je obavezno")
  private String oib;

  private Status status;

}
