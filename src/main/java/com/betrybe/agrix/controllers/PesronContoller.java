package com.betrybe.agrix.controllers;


import com.betrybe.agrix.controllers.dto.FarmDto;
import com.betrybe.agrix.controllers.dto.PersonDto;
import com.betrybe.agrix.controllers.dto.ResponseDto;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Person Controller.
 */
@RestController
@RequestMapping("/persons")
public class PesronContoller {

  private PersonService personService;

  /**
   * PersonController constructor.
   */
  @Autowired
  public PesronContoller(PersonService personService) {
    this.personService = personService;
  }

  @PostMapping()
  public ResponseEntity<?> createFarm(@RequestBody PersonDto personDto) {
    Person newPerson = personService.create(personDto.toPerson());
    PersonDto.ToResponse savedPerson = PersonDto.ToResponse.fromEntity(newPerson);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(savedPerson);
  }
}
