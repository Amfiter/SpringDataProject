package com.syncretis.SpringDataProject.controllers;

import com.syncretis.SpringDataProject.dto.PersonDTO;
import com.syncretis.SpringDataProject.models.Person;
import com.syncretis.SpringDataProject.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/persons")
public class PersonController {

    private final PersonService personService;

    @Autowired
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
    public void createNewPerson(@RequestBody PersonDTO personDTO) {
        personService.addNewPerson(personDTO);
    }

    @DeleteMapping(path = "{id}")
    public void deletePerson(@PathVariable("id") Long id) {
        personService.deletePersons(id);
    }

    @PutMapping(path = "{id}")
    public PersonDTO updatePerson(@RequestBody PersonDTO personDTO, @PathVariable("id") Long id) {
        return personService.updatePerson(personDTO, id);
    }
}
