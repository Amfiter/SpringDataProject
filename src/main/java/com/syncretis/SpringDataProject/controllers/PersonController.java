package com.syncretis.SpringDataProject.controllers;

import com.syncretis.SpringDataProject.dto.PersonDTO;
import com.syncretis.SpringDataProject.entities.Person;
import com.syncretis.SpringDataProject.services.PersonService;
import com.syncretis.SpringDataProject.util.Marker;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonDTO> getPersons() {
        return personService.getPersons();
    }

    @GetMapping(path = "{id}")
    public PersonDTO getPersonById(@PathVariable("id") Long id) {
        return personService.getPersons(id);
    }

    @PostMapping
    @Validated({Marker.OnCreate.class})
    public void createNewPerson(@Valid @RequestBody PersonDTO personDTO) {
        personService.addNewPerson(personDTO);
    }

    @DeleteMapping(path = "{id}")
    public void deletePerson(@PathVariable("id") Long id) {
        personService.deletePersons(id);
    }

    @Validated({Marker.OnUpdate.class})
    @PutMapping(path = "{id}")
    public Person updatePerson(@RequestBody PersonDTO personDTO, @PathVariable("id") Long id) {
        return personService.updatePerson(personDTO, id);
    }
}
