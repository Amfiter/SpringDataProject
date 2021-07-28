package com.syncretis.SpringDataProject.controllers;

import com.syncretis.SpringDataProject.models.Person;
import com.syncretis.SpringDataProject.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/get")
    public List<Person> getPerson() {
        return personService.getPersons();
    }

    @PostMapping(value = "/post")
    public void createNewPerson(@RequestBody Person person) {
        personService.addNewPerson(person);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deletePerson(@PathVariable("id") Long id) {
        personService.deletePersons(id);
    }
}
