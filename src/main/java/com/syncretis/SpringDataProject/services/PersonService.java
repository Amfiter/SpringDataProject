package com.syncretis.SpringDataProject.services;

import com.syncretis.SpringDataProject.models.Person;
import com.syncretis.SpringDataProject.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public void addNewPerson(Person person) {
        System.out.println(person);
        personRepository.save(person);
    }

    public void deletePersons(Long id) {
        boolean personExists = personRepository.existsById(id);
        if (!personExists) {
            throw new IllegalStateException("person with id = " + id + " does't exist ");
        } else {
            personRepository.deleteById(id);
        }
    }

}
