package com.syncretis.SpringDataProject.services;

import com.syncretis.SpringDataProject.models.Person;
import com.syncretis.SpringDataProject.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Person> getPersons(Long personId) {
        return personRepository.findById(personId);
    }

    public void addNewPerson(Person person) {
        System.out.println(person);
        Optional<Person> personOptional = personRepository.findPersonBySecondName(person.getSecondName());
        if (personOptional.isPresent()) {
            throw new IllegalStateException("the person already exists");
        }
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

    public Person updatePerson(Person newPerson, Long id) {
        return personRepository.findById(id)
                .map(person -> {
                    person.setFirstName(newPerson.getFirstName());
                    person.setSecondName(newPerson.getSecondName());
                    person.setBirthday(newPerson.getBirthday());
                    return personRepository.save(person);
                })
                .orElseThrow(() -> new IllegalStateException("Person with id =" + id + " does not exist"));
    }

}
